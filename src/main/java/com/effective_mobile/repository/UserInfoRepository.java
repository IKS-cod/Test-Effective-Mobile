package com.effective_mobile.repository;

import com.effective_mobile.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для доступа к информации о пользователях в базе данных.
 * <p>
 * Этот интерфейс расширяет JpaRepository, что позволяет выполнять стандартные операции CRUD
 * для сущности UserInfo. Также включает в себя пользовательский метод для поиска пользователя по адресу электронной почты.
 * </p>
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    /**
     * Получает сущность UserInfo по адресу электронной почты.
     *
     * @param email адрес электронной почты пользователя, которого нужно получить
     * @return Optional, содержащий UserInfo, если пользователь найден, или пустой Optional, если пользователь с данным адресом не существует
     */
    Optional<UserInfo> findByEmail(String email);
}
