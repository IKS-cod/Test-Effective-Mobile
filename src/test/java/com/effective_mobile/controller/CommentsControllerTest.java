package com.effective_mobile.controller;

import com.effective_mobile.dto.AuthRequest;
import com.effective_mobile.dto.CommentFromDbDto;
import com.effective_mobile.dto.CreateOrUpdateCommentsDto;
import com.effective_mobile.enums.Role;
import com.effective_mobile.model.Task;
import com.effective_mobile.model.UserInfo;
import com.effective_mobile.repository.CommentRepository;
import com.effective_mobile.repository.TaskRepository;
import com.effective_mobile.repository.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.effective_mobile.enums.Priority.HIGH;
import static com.effective_mobile.enums.Status.PENDING;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class CommentsControllerTest {

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    public void setUp() {
        // Очистка БД
        commentRepository.deleteAll();
        taskRepository.deleteAll();
        userInfoRepository.deleteAll();
    }

    @Test
    @DisplayName("Проверка метода создания комментария")
    void createComment() throws Exception {
        // Подготовка данных
        String emailUser = "user@example.com";
        String emailAdmin = "admin@example.com";
        String password = "password";

        // Создание пользователей
        UserInfo userInfoUser = new UserInfo();
        userInfoUser.setRole(Role.USER);
        userInfoUser.setEmail(emailUser);
        userInfoUser.setPassword(passwordEncoder.encode(password));
        userInfoRepository.save(userInfoUser);

        UserInfo userInfoAdmin = new UserInfo();
        userInfoAdmin.setRole(Role.ADMIN);
        userInfoAdmin.setEmail(emailAdmin);
        userInfoAdmin.setPassword(passwordEncoder.encode(password));
        userInfoRepository.save(userInfoAdmin);

        // Создание задачи
        Task task = new Task();
        task.setTitle("Task");
        task.setStatus(PENDING);
        task.setPriority(HIGH);
        task.setAuthor(userInfoAdmin);
        task.setAssignee(userInfoUser);
        taskRepository.save(task);

        // Получение токена для авторизации
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(emailAdmin);
        authRequest.setPassword(password);
        ResponseEntity<String> response = testRestTemplate.postForEntity("/login", authRequest, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        String token = response.getBody();

        // Создание комментария
        CreateOrUpdateCommentsDto createOrUpdateCommentsDto = new CreateOrUpdateCommentsDto();
        createOrUpdateCommentsDto.setText("Test comment");

        // Создание HTTP-запроса
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<CreateOrUpdateCommentsDto> requestEntity = new HttpEntity<>(createOrUpdateCommentsDto, headers);

        // Выполнение POST-запроса
        ResponseEntity<CommentFromDbDto> responseEntity = testRestTemplate.exchange(
                "/comments/" + task.getId(),
                HttpMethod.POST,
                requestEntity,
                CommentFromDbDto.class
        );

        // Проверки
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getText()).isEqualTo(createOrUpdateCommentsDto.getText());
    }
}
