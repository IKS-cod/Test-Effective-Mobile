package com.effective_mobile.exception;

/**
 * Исключение, выбрасываемое при попытке использовать недопустимую или некорректную роль.
 *
 */
public class IllegalRoleException extends RuntimeException {

  /**
   * Конструктор для создания исключения с пользовательским сообщением.
   *
   * @param message сообщение, описывающее причину исключения
   */
  public IllegalRoleException(String message) {
    super(message);
  }
}

