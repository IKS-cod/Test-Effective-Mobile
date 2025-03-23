package com.effective_mobile.controller;

import com.effective_mobile.dto.AuthRequest;
import com.effective_mobile.enums.Role;
import com.effective_mobile.model.UserInfo;
import com.effective_mobile.repository.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class AuthControllerTest {

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

    @BeforeEach
    public void setUp() {
        // Очистка БД
        userInfoRepository.deleteAll();
    }

    @Test
    @DisplayName("Проверка успешной авторизации")
    void successfulLogin() throws Exception {
        // Подготовка данных
        String email = "user@example.com";
        String password = "password";

        // Создание пользователя
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        userInfo.setPassword(passwordEncoder.encode(password));
        userInfo.setRole(Role.USER);
        userInfoRepository.save(userInfo);

        // Создание запроса на авторизацию
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(email);
        authRequest.setPassword(password);

        // Выполнение POST-запроса
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("/login", authRequest, String.class);

        // Проверки
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    @DisplayName("Проверка неуспешной авторизации (неверный пароль)")
    void unsuccessfulLoginWrongPassword() throws Exception {
        // Подготовка данных
        String email = "user@example.com";
        String password = "password";

        // Создание пользователя
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(email);
        userInfo.setPassword(passwordEncoder.encode(password));
        userInfo.setRole(Role.USER);
        userInfoRepository.save(userInfo);

        // Создание запроса на авторизацию с неверным паролем
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(email);
        authRequest.setPassword("wrongpassword");

        // Выполнение POST-запроса
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("/login", authRequest, String.class);

        // Проверки
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(401);
        assertThat(responseEntity.getBody()).isNull();
    }

    @Test
    @DisplayName("Проверка неуспешной авторизации (пользователь не существует)")
    void unsuccessfulLoginUserDoesNotExist() throws Exception {
        // Подготовка данных
        String email = "nonexistent@example.com";
        String password = "password";

        // Создание запроса на авторизацию для несуществующего пользователя
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail(email);
        authRequest.setPassword(password);

        // Выполнение POST-запроса
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("/login", authRequest, String.class);

        // Проверки
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(401);
        assertThat(responseEntity.getBody()).isNull();
    }
}
