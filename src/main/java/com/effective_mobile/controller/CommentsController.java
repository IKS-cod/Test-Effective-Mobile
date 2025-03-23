package com.effective_mobile.controller;

import com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod;
import com.effective_mobile.aspect.annotation.CustomLoggingStartMethod;
import com.effective_mobile.dto.CommentFromDbDto;
import com.effective_mobile.dto.CreateOrUpdateCommentsDto;
import com.effective_mobile.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для обработки запросов, связанных с комментариями.
 *
 */
@RestController
@RequestMapping("/comments")
@Tag(name = "Комментарии")
public class CommentsController {

    /**
     * Логгер для записи информации о выполнении методов контроллера.
     */
    private static final Logger logger = LoggerFactory.getLogger(CommentsController.class);

    /**
     * Сервис для работы с комментариями, предоставляющий бизнес-логику для создания и управления комментариями.
     */
    private final CommentService commentService;

    /**
     * Конструктор для инициализации сервиса комментариев.
     *
     * @param commentService сервис для работы с комментариями
     */
    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Метод для создания нового комментария.
     *
     * @param id идентификатор сущности, к которой относится комментарий
     * @param createOrUpdateCommentsDto объект, содержащий данные для создания комментария
     * @return созданный комментарий в виде DTO
     *
     * @note Доступно только для авторизованных пользователей, которые прошли верификацию для комментирования, или для пользователей с ролью 'ROLE_ADMIN'.
     */
    @PreAuthorize("@userVerification.verificationUserForComment(#id) || hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{id}")
    @Operation(summary = "Создание комментария")
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public CommentFromDbDto createComment(
            @PathVariable Long id,
            @RequestBody CreateOrUpdateCommentsDto createOrUpdateCommentsDto) {
        return commentService.createComment(id, createOrUpdateCommentsDto);
    }
}

