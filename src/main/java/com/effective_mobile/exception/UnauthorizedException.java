package com.effective_mobile.exception;

/**
 * Исключение, выбрасываемое при попытке выполнить действие без необходимых прав доступа или авторизации.
 *
 */
public class UnauthorizedException extends RuntimeException {

  /**
   * Конструктор для создания исключения с пользовательским сообщением.
   *
   * @param message сообщение, описывающее причину исключения
   */
  public UnauthorizedException(String message) {
    super(message);
  }
}

