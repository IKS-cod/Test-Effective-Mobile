package com.effective_mobile.service.validation;

import com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod;
import com.effective_mobile.aspect.annotation.CustomLoggingStartMethod;
import com.effective_mobile.dto.CreateUserInfoDto;
import com.effective_mobile.enums.Role;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Сервис для проверки валидности роли пользователя.
 *
 */
@Service
public class ValidRole {

    /**
     * Проверяет, является ли роль в объекте CreateUserInfoDto допустимой (принадлежит ли перечислению Role).
     *
     * @param createUserInfoDto объект, содержащий роль для проверки
     * @return true, если роль валидна, false иначе
     */
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public boolean isValidRole(CreateUserInfoDto createUserInfoDto) {
        return Arrays.asList(Role.values()).contains(createUserInfoDto.getRole());
    }
}

