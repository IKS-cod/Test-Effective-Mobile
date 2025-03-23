package com.effective_mobile.service.validation;

import com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod;
import com.effective_mobile.aspect.annotation.CustomLoggingStartMethod;
import com.effective_mobile.dto.AuthRequest;
import com.effective_mobile.dto.CreateUserInfoDto;
import org.springframework.stereotype.Service;

@Service
public class ValidEmail {
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public boolean isValidEmail(CreateUserInfoDto createUserInfoDto) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return createUserInfoDto.getEmail() != null && createUserInfoDto.getEmail().matches(emailRegex);
    }
}