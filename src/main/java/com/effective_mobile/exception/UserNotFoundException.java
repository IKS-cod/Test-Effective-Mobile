package com.effective_mobile.exception;

/**
 * Исключение, возникающее, когда пользователь не найден.
 * <p>
 * Это пользовательское исключение расширяет RuntimeException и используется
 * для обработки случаев, когда запрашиваемый пользователь не может быть найден
 * в системе. Это может произойти, например, при попытке получить пользователя
 * по идентификатору или адресу электронной почты, который не существует в базе данных.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Конструктор по умолчанию.
     */
    public UserNotFoundException() {
        super("Пользователь не найден."); // Сообщение по умолчанию
    }

    /**
     * Конструктор с сообщением.
     *
     * @param message сообщение об ошибке
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Конструктор с сообщением и причиной.
     *
     * @param message сообщение об ошибке
     * @param cause   причина возникновения исключения
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Конструктор с причиной.
     *
     * @param cause причина возникновения исключения
     */
    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}