package com.effective_mobile.exception;

/**
 * Исключение, выбрасываемое при попытке использовать недопустимый или некорректный пароль.
 *
 */
public class IllegalPasswordException extends RuntimeException {

    /**
     * Конструктор для создания исключения с пользовательским сообщением.
     *
     * @param message сообщение, описывающее причину исключения
     */
    public IllegalPasswordException(String message) {
        super(message);
    }
}

