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

/**
 * Сервис для работы с информацией о пользователях, реализующий интерфейс {@link org.springframework.security.core.userdetails.UserDetailsService}.
 * Этот сервис предоставляет метод для загрузки информации о пользователе по его email-адресу.
 *
 */
@Service
public class UserService implements UserDetailsService {

    /**
     * Репозиторий для доступа к информации о пользователях в базе данных.
     */
    private final UserInfoRepository userRepository;

    /**
     * Конструктор для инициализации репозитория пользователей.
     *
     * @param userRepository репозиторий пользователей
     */
    public UserService(UserInfoRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Загружает информацию о пользователе по его email-адресу.
     *
     * @param email email-адрес пользователя
     * @return объект UserDetails, содержащий информацию о пользователе
     * @throws UsernameNotFoundException если пользователь с указанным email не найден
     */
    @Override
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с email {} не найден." + email));
        return new CustomUserDetails(userInfo);
    }
}
