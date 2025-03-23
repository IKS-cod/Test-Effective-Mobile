package com.effective_mobile.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Этот аспект используется для логирования методов, помеченных аннотацией {@link com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod},
 * после их успешного выполнения.
 *
 */
@Aspect
@Component
public class LoggingFinishedMethodAspect {

    /**
     * Логгер для записи информации о выполнении методов.
     */
    private static final Logger logger = LoggerFactory.getLogger(LoggingFinishedMethodAspect.class);

    /**
     * Метод, вызываемый после успешного выполнения метода, помеченного аннотацией {@link com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod}.
     *
     * @param joinPoint точка соединения, предоставляющая информацию о вызываемом методе
     * @param result результат выполнения метода
     */
    @AfterReturning(pointcut = "@annotation(com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod)",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method {} has successfully completed. Result: {}",
                joinPoint.getSignature().getName(),
                result);
    }
}

