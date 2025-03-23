package com.effective_mobile.service;

import com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod;
import com.effective_mobile.aspect.annotation.CustomLoggingStartMethod;
import com.effective_mobile.dto.CommentFromDbDto;
import com.effective_mobile.dto.CreateOrUpdateCommentsDto;
import com.effective_mobile.exception.TaskNotFoundException;
import com.effective_mobile.model.Comments;
import com.effective_mobile.model.Task;
import com.effective_mobile.repository.CommentRepository;
import com.effective_mobile.repository.TaskRepository;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с комментариями к заданиям.
 *
 */
@Service
public class CommentService {

    /**
     * Репозиторий для доступа к комментариям в базе данных.
     */
    private final CommentRepository commentRepository;

    /**
     * Репозиторий для доступа к заданиям в базе данных.
     */
    private final TaskRepository taskRepository;

    /**
     * Конструктор для инициализации репозиториев.
     *
     * @param commentRepository репозиторий комментариев
     * @param taskRepository репозиторий заданий
     */
    public CommentService(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    /**
     * Создает новый комментарий к заданию с заданным идентификатором.
     *
     * @param id идентификатор задания
     * @param createOrUpdateCommentsDto объект, содержащий текст комментария
     * @return созданный комментарий в виде DTO
     * @throws TaskNotFoundException если задание с указанным идентификатором не найдено
     */
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public CommentFromDbDto createComment(Long id, CreateOrUpdateCommentsDto createOrUpdateCommentsDto) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Задача с идентификатором {} не найдена." + id));

        Comments commentsForDb = new Comments();
        commentsForDb.setTask(task);
        commentsForDb.setText(createOrUpdateCommentsDto.getText());

        Comments commentsFromDb = commentRepository.save(commentsForDb);

        CommentFromDbDto commentFromDbDto = new CommentFromDbDto();
        commentFromDbDto.setId(commentsFromDb.getId());
        commentFromDbDto.setText(commentsFromDb.getText());
        commentFromDbDto.setTitleTask(commentsFromDb.getTask().getTitle());

        return commentFromDbDto;
    }
}
