package com.effective_mobile.service;

import com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod;
import com.effective_mobile.aspect.annotation.CustomLoggingStartMethod;
import com.effective_mobile.exception.TaskNotFoundException;
import com.effective_mobile.model.Task;
import com.effective_mobile.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserVerification {

    private static final Logger logger = LoggerFactory.getLogger(UserVerification.class);

    private final TaskRepository taskRepository;
    public UserVerification(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public boolean verificationUserForComment(Long taskId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + taskId));

        boolean hasPermission = task.getAssignee().getEmail().equals(authentication.getName());

        if (hasPermission) {
            logger.info("Пользователь {} имеет право добавлять комментарии к задаче с ID: {}",
                    authentication.getName(), taskId);
        } else {
            logger.warn("Пользователь {} не имеет права добавлять комментарии к задаче с ID: {}",
                    authentication.getName(), taskId);
        }

        return hasPermission;
    }
}