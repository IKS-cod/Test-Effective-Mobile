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

@RestController
@RequestMapping("/tasks")
@Tag(name = "Задания")

public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    @Operation(summary = "Создание задания")
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public TaskFromDbDto createTask(@RequestBody CreateTaskDto createTaskDto) {
        return taskService.createTask(createTaskDto);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Получение информации о задании по id")
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public TaskWithCommentsFromDbDto getTask(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Обновление информации задания по id")
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public TaskFromDbDto updateTask(@PathVariable Long id, @RequestBody UpdateTaskDto updateTaskDto) {
        return taskService.updateTask(id, updateTaskDto);
    }

    @PreAuthorize("@userVerification.verificationUserForComment(#id) || hasAuthority('ROLE_ADMIN')")
    @PutMapping("/status/{id}")
    @Operation(summary = "Обновление статуса задания по id")
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public TaskFromDbDto updateTaskStatus(@PathVariable Long id, @RequestBody StatusTaskDto statusTaskDto) {
        return taskService.updateTaskStatus(id, statusTaskDto);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление задания по id")
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

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
