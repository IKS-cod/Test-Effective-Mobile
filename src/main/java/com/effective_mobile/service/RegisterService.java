package com.effective_mobile.service;

import com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod;
import com.effective_mobile.aspect.annotation.CustomLoggingStartMethod;
import com.effective_mobile.dto.CreateUserInfoDto;
import com.effective_mobile.dto.UserDto;
import com.effective_mobile.exception.IllegalEmailException;
import com.effective_mobile.exception.IllegalPasswordException;
import com.effective_mobile.exception.IllegalRoleException;
import com.effective_mobile.exception.UserAlreadyExistsException;
import com.effective_mobile.mapper.Mappers;
import com.effective_mobile.model.UserInfo;
import com.effective_mobile.repository.UserInfoRepository;
import com.effective_mobile.service.validation.ValidEmail;
import com.effective_mobile.service.validation.ValidPassword;
import com.effective_mobile.service.validation.ValidRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final ValidRole validRole;
    private final ValidPassword validPassword;
    private final ValidEmail validEmail;
    private final Mappers mappers;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(ValidRole validRole,
                           ValidPassword validPassword,
                           ValidEmail validEmail,
                           Mappers mappers,
                           UserInfoRepository userInfoRepository,
                           PasswordEncoder passwordEncoder) {
        this.validRole = validRole;
        this.validPassword = validPassword;
        this.validEmail = validEmail;
        this.mappers = mappers;
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public UserDto register(CreateUserInfoDto createUserInfoDto) {
        validateCreateUserInfoDto(createUserInfoDto);
        if (userInfoRepository.findByEmail(createUserInfoDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует."
                    + createUserInfoDto.getEmail());
        }
        UserInfo userInfoForDb = new UserInfo();
        userInfoForDb.setEmail(createUserInfoDto.getEmail());
        userInfoForDb.setPassword(passwordEncoder.encode(createUserInfoDto.getPassword()));
        userInfoForDb.setRole(createUserInfoDto.getRole());
        UserInfo userInfoFromDb = userInfoRepository.save(userInfoForDb);
        return mappers.toUserDto(userInfoFromDb);
    }

    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    private void validateCreateUserInfoDto(CreateUserInfoDto createUserInfoDto) {
        if (!validEmail.isValidEmail(createUserInfoDto)) {
            throw new IllegalEmailException("Invalid Email");
        }
        if (!validPassword.isValidPassword(createUserInfoDto)) {
            throw new IllegalPasswordException("Invalid Password");
        }
        if (!validRole.isValidRole(createUserInfoDto)) {
            throw new IllegalRoleException("Invalid Role");
        }
    }
}