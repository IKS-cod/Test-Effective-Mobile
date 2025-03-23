package com.effective_mobile.service.validation;

import com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod;
import com.effective_mobile.aspect.annotation.CustomLoggingStartMethod;
import com.effective_mobile.dto.CreateUserInfoDto;
import org.springframework.stereotype.Service;

@Service
public class ValidPassword {
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public boolean isValidPassword(CreateUserInfoDto createUserInfoDto) {
        return createUserInfoDto.getPassword().length() >= 6;
    }
}
