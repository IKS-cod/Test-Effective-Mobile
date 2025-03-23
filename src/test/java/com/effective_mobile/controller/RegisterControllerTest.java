package com.effective_mobile.controller;

import com.effective_mobile.dto.CreateUserInfoDto;
import com.effective_mobile.dto.UserDto;
import com.effective_mobile.enums.Role;
import com.effective_mobile.model.UserInfo;
import com.effective_mobile.repository.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class RegisterControllerTest {

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
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        // Очистка БД
        userInfoRepository.deleteAll();
    }

    @Test
    @DisplayName("Проверка метода регистрации пользователя")
    void registerUser() throws Exception {
        // Подготовка данных
        String email = "user@example.com";
        String password = "password";

        // Создание тестового объекта CreateUserInfoDto
        CreateUserInfoDto createUserInfoDto = new CreateUserInfoDto();
        createUserInfoDto.setEmail(email);
        createUserInfoDto.setPassword(password);
        createUserInfoDto.setRole(Role.USER); // Если поле роли есть

        // Создание HTTP-запроса
        HttpEntity<CreateUserInfoDto> requestEntity = new HttpEntity<>(createUserInfoDto);

        // Выполнение POST-запроса
        ResponseEntity<UserDto> responseEntity = testRestTemplate.exchange(
                "/register",
                HttpMethod.POST,
                requestEntity,
                UserDto.class
        );

        // Проверки
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getEmail()).isEqualTo(email);

        // Проверка, что пользователь создан в БД
        Optional<UserInfo> savedUserOptional = userInfoRepository.findByEmail(email);
        assertThat(savedUserOptional).isPresent();
        UserInfo savedUser = savedUserOptional.get();
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo(email);
        // Проверка, что пароль зашифрован
        assertThat(passwordEncoder.matches(password, savedUser.getPassword())).isTrue();
    }
}
