package com.effective_mobile.service;

import com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod;
import com.effective_mobile.aspect.annotation.CustomLoggingStartMethod;
import com.effective_mobile.exception.UserNotFoundException;
import com.effective_mobile.model.CustomUserDetails;
import com.effective_mobile.model.UserInfo;
import com.effective_mobile.repository.UserInfoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserInfoRepository userRepository;

    public UserService(UserInfoRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с email {} не найден." + email));
        return new CustomUserDetails(userInfo);
    }
}