package com.effective_mobile.controller;

import com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod;
import com.effective_mobile.aspect.annotation.CustomLoggingStartMethod;
import com.effective_mobile.dto.CreateUserInfoDto;
import com.effective_mobile.dto.UserDto;
import com.effective_mobile.service.RegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Контроллер для обработки запросов регистрации пользователей.
 *
 */
@RestController
@Tag(name = "Регистрация")
@RequestMapping("/register")
public class RegisterController {

    /**
     * Сервис для обработки регистрации пользователей, предоставляющий бизнес-логику для создания новых аккаунтов.
     */
    private final RegisterService registerService;

    /**
     * Конструктор для инициализации сервиса регистрации.
     *
     * @param registerService сервис для обработки регистрации пользователей
     */
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    /**
     * Метод для регистрации нового пользователя.
     *
     * @param createUserInfoDto объект, содержащий данные для создания нового пользователя
     * @return зарегистрированный пользователь в виде DTO
     * @throws IOException если возникает ошибка при обработке запроса
     */
    @PostMapping
    @Operation(summary = "Регистрация пользователя")
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public UserDto register(@RequestBody CreateUserInfoDto createUserInfoDto) throws IOException {
        return registerService.register(createUserInfoDto);
    }
}

