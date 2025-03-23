package com.effective_mobile.enums;

/**
 * Перечисление ролей пользователей в системе.
 *
 */
public enum Role {
    /**
     * Роль обычного пользователя.
     */
    USER,
    /**
     * Роль администратора.
     */
    ADMIN;

    /**
     * Возвращает строковое представление текущей роли.
     *
     * @return строковое представление
     */
    @Override
    public String toString() {
        return name();
    }
}
