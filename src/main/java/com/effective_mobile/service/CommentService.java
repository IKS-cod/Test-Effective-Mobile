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

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    public CommentService(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }
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