package com.effective_mobile.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Этот аспект используется для логирования методов, помеченных аннотацией {@link com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod},
 * перед их вызовом.
 *
 */
@Aspect
@Component
public class LoggingStartMethodAspect {

    /**
     * Логгер для записи информации о начале выполнения методов.
     */
    private static final Logger logger = LoggerFactory.getLogger(LoggingStartMethodAspect.class);

    /**
     * Метод, вызываемый перед вызовом метода, помеченного аннотацией {@link com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod}.
     *
     * @param joinPoint точка соединения, предоставляющая информацию о вызываемом методе
     */
    @Before("@annotation(com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod)")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("Method {} is about to be called.",
                joinPoint.getSignature().getName());
    }
}

