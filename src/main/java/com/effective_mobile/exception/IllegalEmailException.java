package com.effective_mobile.exception;

/**
 * Исключение, выбрасываемое при попытке использовать недопустимый или некорректный email.
 *
 */
public class IllegalEmailException extends RuntimeException {

    /**
     * Конструктор для создания исключения с пользовательским сообщением.
     *
     * @param message сообщение, описывающее причину исключения
     */
    public IllegalEmailException(String message) {
        super(message);
    }
}

