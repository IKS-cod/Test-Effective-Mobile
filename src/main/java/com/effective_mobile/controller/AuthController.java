package com.effective_mobile.controller;

import com.effective_mobile.dto.AuthRequest;
import com.effective_mobile.model.JwtUtil;
import com.effective_mobile.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для обработки запросов авторизации пользователей.
 *
 */
@RestController
@Tag(name = "Авторизация")
@RequestMapping("/login")
public class AuthController {

    /**
     * Менеджер аутентификации, используемый для проверки учетных данных пользователя.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Сервис для работы с пользователями, предоставляющий информацию о пользователе.
     */
    private final UserService userService;

    /**
     * Утилита для работы с токенами JWT, генерирующая токены аутентификации.
     */
    private final JwtUtil jwtUtil;

    /**
     * Конструктор для инициализации зависимостей контроллера.
     *
     * @param authenticationManager менеджер аутентификации
     * @param userService сервис для работы с пользователями
     * @param jwtUtil утилита для работы с токенами JWT
     */
    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Метод для обработки запроса авторизации пользователя.
     *
     * @param authRequest объект, содержащий учетные данные пользователя (email и пароль)
     * @return ответ с токеном JWT, если авторизация успешна, или статус 401, если авторизация не удалась
     */
    @PostMapping
    @Operation(summary = "Авторизация пользователя")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
            final UserDetails userDetails = userService.loadUserByUsername(authRequest.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

