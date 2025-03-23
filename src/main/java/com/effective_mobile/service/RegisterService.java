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

/**
 * Сервис для регистрации новых пользователей в системе.
 *
 */
@Service
public class RegisterService {

    /**
     * Сервис для проверки валидности роли пользователя.
     */
    private final ValidRole validRole;

    /**
     * Сервис для проверки валидности пароля пользователя.
     */
    private final ValidPassword validPassword;

    /**
     * Сервис для проверки валидности email-адреса пользователя.
     */
    private final ValidEmail validEmail;

    /**
     * Маппер для преобразования между объектами UserInfo и UserDto.
     */
    private final Mappers mappers;

    /**
     * Репозиторий для доступа к информации о пользователях в базе данных.
     */
    private final UserInfoRepository userInfoRepository;

    /**
     * Кодировщик паролей для безопасного хранения паролей пользователей.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Конструктор для инициализации сервисов и репозиториев.
     *
     * @param validRole сервис для проверки роли
     * @param validPassword сервис для проверки пароля
     * @param validEmail сервис для проверки email
     * @param mappers маппер для преобразования объектов
     * @param userInfoRepository репозиторий пользователей
     * @param passwordEncoder кодировщик паролей
     */
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

    /**
     * Регистрирует нового пользователя в системе.
     *
     * @param createUserInfoDto объект, содержащий информацию о новом пользователе
     * @return зарегистрированный пользователь в виде DTO
     * @throws UserAlreadyExistsException если пользователь с таким email уже существует
     * @throws IllegalEmailException если email-адрес невалиден
     * @throws IllegalPasswordException если пароль невалиден
     * @throws IllegalRoleException если роль невалидна
     */
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

    /**
     * Проверяет валидность объекта CreateUserInfoDto.
     *
     * @param createUserInfoDto объект для проверки
     * @throws IllegalEmailException если email-адрес невалиден
     * @throws IllegalPasswordException если пароль невалиден
     * @throws IllegalRoleException если роль невалидна
     */
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
