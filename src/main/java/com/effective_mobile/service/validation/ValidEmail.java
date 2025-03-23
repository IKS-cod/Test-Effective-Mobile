package com.effective_mobile.service.validation;

import com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod;
import com.effective_mobile.aspect.annotation.CustomLoggingStartMethod;
import com.effective_mobile.dto.CreateUserInfoDto;
import org.springframework.stereotype.Service;

/**
 * Сервис для проверки валидности email-адреса.
 *
 */
@Service
public class ValidEmail {

    /**
     * Проверяет, является ли email-адрес в объекте CreateUserInfoDto валидным.
     *
     * @param createUserInfoDto объект, содержащий email для проверки
     * @return true, если email валиден, false иначе
     */
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public boolean isValidEmail(CreateUserInfoDto createUserInfoDto) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return createUserInfoDto.getEmail() != null && createUserInfoDto.getEmail().matches(emailRegex);
    }
}
