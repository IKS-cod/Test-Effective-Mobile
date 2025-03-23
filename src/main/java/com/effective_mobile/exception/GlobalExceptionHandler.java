package com.effective_mobile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Глобальный обработчик исключений, обрабатывающий различные типы ошибок и возвращающий соответствующие HTTP-статусы.
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обработчик исключений, связанных с отсутствием ресурсов (404 NOT FOUND).
     *
     * @param ex исключение, содержащее сообщение об ошибке
     * @return ответ с сообщением об ошибке и статусом 404
     */
    @ExceptionHandler({
            UserNotFoundException.class,
            TaskNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Обработчик исключений, связанных с неверными данными или нарушением бизнес-логики (400 BAD REQUEST).
     *
     * @param ex исключение, содержащее сообщение об ошибке
     * @return ответ с сообщением об ошибке и статусом 400
     */
    @ExceptionHandler({
            UserAlreadyExistsException.class,
            IllegalPasswordException.class,
            IllegalEmailException.class,
            IllegalRoleException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleBadRequestException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Обработчик исключений, связанных с отсутствием авторизации (401 UNAUTHORIZED).
     *
     * @param ex исключение, содержащее сообщение об ошибке
     * @return ответ с сообщением об ошибке и статусом 401
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleUnauthorizedException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}

