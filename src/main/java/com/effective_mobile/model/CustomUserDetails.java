package com.effective_mobile.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Реализация интерфейса {@link org.springframework.security.core.userdetails.UserDetails} для работы с пользовательской информацией в Spring Security.
 * Этот класс используется для представления аутентифицированного пользователя в системе.
 *
 * @author [Ваше имя]
 */
public class CustomUserDetails implements UserDetails {

    /**
     * Объект, содержащий информацию о пользователе.
     */
    private final UserInfo userInfo;

    /**
     * Конструктор для инициализации пользовательских данных.
     *
     * @param userInfo объект, содержащий информацию о пользователе
     */
    public CustomUserDetails(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * Возвращает имя пользователя (обычно email).
     *
     * @return имя пользователя
     */
    @Override
    public String getUsername() {
        return userInfo.getEmail();
    }

    /**
     * Возвращает пароль пользователя.
     *
     * @return пароль пользователя
     */
    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    /**
     * Возвращает список авторизаций (ролей) пользователя.
     *
     * @return коллекция ролей пользователя
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "ROLE_" + userInfo.getRole().name();
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    /**
     * Указывает, не истек ли срок действия учетной записи пользователя.
     *
     * @return всегда true, так как срок действия учетной записи не проверяется
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Указывает, не заблокирована ли учетная запись пользователя.
     *
     * @return всегда true, так как блокировка учетной записи не проверяется
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Указывает, не истек ли срок действия учетных данных (пароля) пользователя.
     *
     * @return всегда true, так как срок действия учетных данных не проверяется
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Указывает, активен ли пользователь.
     *
     * @return всегда true, так как статус активности пользователя не проверяется
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
