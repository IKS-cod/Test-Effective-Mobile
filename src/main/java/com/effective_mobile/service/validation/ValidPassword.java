package com.effective_mobile.service.validation;

import com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod;
import com.effective_mobile.aspect.annotation.CustomLoggingStartMethod;
import com.effective_mobile.dto.CreateUserInfoDto;
import org.springframework.stereotype.Service;

/**
 * Сервис для проверки валидности пароля.
 *
 */
@Service
public class ValidPassword {

    /**
     * Проверяет, соответствует ли пароль в объекте CreateUserInfoDto минимальным требованиям по длине (не менее 6 символов).
     *
     * @param createUserInfoDto объект, содержащий пароль для проверки
     * @return true, если пароль валиден, false иначе
     */
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public boolean isValidPassword(CreateUserInfoDto createUserInfoDto) {
        return createUserInfoDto.getPassword().length() >= 6;
    }
}

