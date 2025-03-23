package com.effective_mobile.controller;

import com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod;
import com.effective_mobile.aspect.annotation.CustomLoggingStartMethod;
import com.effective_mobile.dto.*;
import com.effective_mobile.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для обработки запросов, связанных с заданиями.
 *
 */
@RestController
@RequestMapping("/tasks")
@Tag(name = "Задания")
public class TaskController {

    /**
     * Сервис для работы с заданиями, предоставляющий бизнес-логику для создания, обновления и удаления заданий.
     */
    private final TaskService taskService;

    /**
     * Конструктор для инициализации сервиса заданий.
     *
     * @param taskService сервис для работы с заданиями
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Метод для создания нового задания.
     *
     * @param createTaskDto объект, содержащий данные для создания нового задания
     * @return созданное задание в виде DTO
     *
     * @note Доступно только для пользователей с ролью 'ROLE_ADMIN'.
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    @Operation(summary = "Создание задания")
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public TaskFromDbDto createTask(@RequestBody CreateTaskDto createTaskDto) {
        return taskService.createTask(createTaskDto);
    }

    /**
     * Метод для получения информации о задании по его идентификатору.
     *
     * @param id идентификатор задания
     * @return задание с информацией в виде DTO
     *
     * @note Доступно только для пользователей с ролью 'ROLE_ADMIN'.
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Получение информации о задании по id")
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public TaskWithCommentsFromDbDto getTask(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    /**
     * Метод для обновления информации задания по его идентификатору.
     *
     * @param id идентификатор задания
     * @param updateTaskDto объект, содержащий обновленные данные для задания
     * @return обновленное задание в виде DTO
     *
     * @note Доступно только для пользователей с ролью 'ROLE_ADMIN'.
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Обновление информации задания по id")
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public TaskFromDbDto updateTask(@PathVariable Long id, @RequestBody UpdateTaskDto updateTaskDto) {
        return taskService.updateTask(id, updateTaskDto);
    }

    /**
     * Метод для обновления статуса задания по его идентификатору.
     *
     * @param id идентификатор задания
     * @param statusTaskDto объект, содержащий новый статус для задания
     * @return задание с обновленным статусом в виде DTO
     *
     * @note Доступно для пользователей, прошедших верификацию для комментирования, или для пользователей с ролью 'ROLE_ADMIN'.
     */
    @PreAuthorize("@userVerification.verificationUserForComment(#id) || hasAuthority('ROLE_ADMIN')")
    @PutMapping("/status/{id}")
    @Operation(summary = "Обновление статуса задания по id")
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public TaskFromDbDto updateTaskStatus(@PathVariable Long id, @RequestBody StatusTaskDto statusTaskDto) {
        return taskService.updateTaskStatus(id, statusTaskDto);
    }

    /**
     * Метод для удаления задания по его идентификатору.
     *
     * @param id идентификатор задания
     *
     * @note Доступно только для пользователей с ролью 'ROLE_ADMIN'.
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление задания по id")
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    /**
     * Метод для получения заданий с комментариями по автору.
     *
     * @param authorId идентификатор автора
     * @param page номер страницы для пагинации
     * @param size размер страницы для пагинации
     * @return страница заданий с комментариями в виде DTO
     *
     * @note Доступно только для пользователей с ролью 'ROLE_ADMIN'.
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/author/{authorId}")
    @Operation(summary = "Получение заданий с комментариями по автору")
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public Page<TaskAndCommentsForAuthorFromDbDto> getTasksByAuthor(
            @PathVariable Long authorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return taskService.getTasksAndCommentsByAuthor(authorId, page, size);
    }

    /**
     * Метод для получения заданий с комментариями по исполнителю.
     *
     * @param assigneeId идентификатор исполнителя
     * @param page номер страницы для пагинации
     * @param size размер страницы для пагинации
     * @return страница заданий с комментариями в виде DTO
     *
     * @note Доступно только для пользователей с ролью 'ROLE_ADMIN'.
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/assignee/{assigneeId}")
    @Operation(summary = "Получение заданий с комментариями по исполнителю")
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public Page<TaskAndCommentsForAssigneeFromDbDto> getTasksByAssignee(
            @PathVariable Long assigneeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return taskService.getTasksAndCommentsByAssignee(assigneeId, page, size);
    }
}

