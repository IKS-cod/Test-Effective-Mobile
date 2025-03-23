package com.effective_mobile.service.validation;

import com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod;
import com.effective_mobile.aspect.annotation.CustomLoggingStartMethod;
import com.effective_mobile.dto.CreateUserInfoDto;
import com.effective_mobile.enums.Role;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ValidRole {
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public boolean isValidRole(CreateUserInfoDto createUserInfoDto) {
        return Arrays.asList(Role.values()).contains(createUserInfoDto.getRole());
    }
}
