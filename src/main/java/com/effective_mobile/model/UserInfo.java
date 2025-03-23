package com.effective_mobile.model;

import com.effective_mobile.enums.Role;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность, представляющая информацию о пользователе в системе.
 *
 * @author [Ваше имя]
 */
@Entity
public class UserInfo {

    /**
     * Уникальный идентификатор пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Пустой конструктор для использования в JPA.
     */
    public UserInfo() {
    }

    /**
     * Конструктор для создания пользователя с заданными параметрами.
     *
     * @param id идентификатор пользователя
     * @param email email пользователя
     * @param password пароль пользователя
     * @param role роль пользователя
     */
    public UserInfo(Long id, String email, String password, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Возвращает идентификатор пользователя.
     *
     * @return идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор пользователя.
     *
     * @param id идентификатор
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает email пользователя.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Устанавливает email пользователя.
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Возвращает пароль пользователя.
     *
     * @return пароль
     */
    public String getPassword() {
        return password;
    }

    /**
     * Устанавливает пароль пользователя.
     *
     * @param password пароль
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Возвращает роль пользователя.
     *
     * @return роль
     */
    public Role getRole() {
        return role;
    }

    /**
     * Устанавливает роль пользователя.
     *
     * @param role роль
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Проверяет равенство текущего пользователя с другим объектом.
     *
     * @param o объект для сравнения
     * @return true, если объекты равны, false иначе
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(id, userInfo.id) &&
                Objects.equals(email, userInfo.email) &&
                Objects.equals(password, userInfo.password) &&
                role == userInfo.role;
    }

    /**
     * Возвращает хэш-код пользователя.
     *
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, role);
    }

    /**
     * Возвращает строковое представление пользователя.
     *
     * @return строковое представление
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
