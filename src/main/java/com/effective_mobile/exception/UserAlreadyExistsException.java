package com.effective_mobile.exception;

/**
 * Исключение, выбрасываемое при попытке зарегистрировать пользователя, который уже существует в системе.
 *
 */
public class UserAlreadyExistsException extends RuntimeException {

    /**
     * Конструктор для создания исключения с пользовательским сообщением.
     *
     * @param message сообщение, описывающее причину исключения
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
