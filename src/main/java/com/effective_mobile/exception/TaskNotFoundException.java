package com.effective_mobile.exception;

/**
 * Исключение, возникающее, когда задача не найдена.
 * <p>
 * Это пользовательское исключение расширяет RuntimeException и используется
 * для обработки случаев, когда запрашиваемая задача не может быть найдена
 * в системе. Это может произойти, например, при попытке получить задачу
 * по идентификатору, который не существует в базе данных.
 */
public class TaskNotFoundException extends RuntimeException {

    /**
     * Конструктор по умолчанию.
     */
    public TaskNotFoundException() {
        super("Задача не найдена."); // Сообщение по умолчанию
    }

    /**
     * Конструктор с сообщением.
     *
     * @param message сообщение об ошибке
     */
    public TaskNotFoundException(String message) {
        super(message);
    }

    /**
     * Конструктор с сообщением и причиной.
     *
     * @param message сообщение об ошибке
     * @param cause   причина возникновения исключения
     */
    public TaskNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Конструктор с причиной.
     *
     * @param cause причина возникновения исключения
     */
    public TaskNotFoundException(Throwable cause) {
        super(cause);
    }
}