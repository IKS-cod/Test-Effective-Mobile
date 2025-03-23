package com.effective_mobile.dto;

import com.effective_mobile.enums.Role;

/**
 * Класс, представляющий данные для создания нового пользователя.
 * Используется для передачи информации о пользователе от клиента к серверу.
 *
 */
public class CreateUserInfoDto {

    /**
     * Email пользователя.
     */
    private String email;

    /**
     * Пароль пользователя.
     */
    private String password;

    /**
     * Роль пользователя.
     */
    private Role role;

    /**
     * Возвращает email пользователя.
     *
     * @return email пользователя
     */
    public String getEmail() {
        return email;
    }

    /**
     * Возвращает пароль пользователя.
     *
     * @return пароль пользователя
     */
    public String getPassword() {
        return password;
    }

    /**
     * Возвращает роль пользователя.
     *
     * @return роль пользователя
     */
    public Role getRole() {
        return role;
    }

    /**
     * Устанавливает email пользователя.
     *
     * @param email email пользователя
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Устанавливает пароль пользователя.
     *
     * @param password пароль пользователя
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Устанавливает роль пользователя.
     *
     * @param role роль пользователя
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
