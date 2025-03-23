package com.effective_mobile.dto;

import com.effective_mobile.enums.Priority;
import com.effective_mobile.enums.Status;

import java.util.List;

/**
 * Класс, представляющий задачу с комментариями, полученную из базы данных.
 * Используется для передачи полной информации о задаче и связанных с ней комментариях,
 * включая текст каждого комментария.
 *
 */
public class TaskWithCommentsFromDbDto {

    /**
     * Идентификатор задачи.
     */
    private Long id;

    /**
     * Название задачи.
     */
    private String title;

    /**
     * Описание задачи.
     */
    private String description;

    /**
     * Статус задачи.
     */
    private Status status;

    /**
     * Приоритет задачи.
     */
    private Priority priority;

    /**
     * Email автора задачи.
     */
    private String emailAuthor;

    /**
     * Email исполнителя задачи.
     */
    private String emailAssignee;

    /**
     * Список комментариев к задаче, каждый из которых представлен как {@link CommentTextFromDbDto}.
     */
    private List<CommentTextFromDbDto> comments;

    /**
     * Создает новый объект TaskWithCommentsFromDbDto.
     */
    public TaskWithCommentsFromDbDto() {
    }

    /**
     * Создает новый объект TaskWithCommentsFromDbDto с заданными параметрами.
     *
     * @param id Идентификатор задачи.
     * @param title Название задачи.
     * @param description Описание задачи.
     * @param status Статус задачи.
     * @param priority Приоритет задачи.
     * @param emailAuthor Email автора задачи.
     * @param emailAssignee Email исполнителя задачи.
     * @param comments Список комментариев к задаче.
     */
    public TaskWithCommentsFromDbDto(Long id, String title, String description, Status status, Priority priority,
                                     String emailAuthor, String emailAssignee, List<CommentTextFromDbDto> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.emailAuthor = emailAuthor;
        this.emailAssignee = emailAssignee;
        this.comments = comments;
    }

    /**
     * Возвращает идентификатор задачи.
     *
     * @return Идентификатор задачи.
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор задачи.
     *
     * @param id Идентификатор задачи.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает название задачи.
     *
     * @return Название задачи.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает название задачи.
     *
     * @param title Название задачи.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Возвращает описание задачи.
     *
     * @return Описание задачи.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание задачи.
     *
     * @param description Описание задачи.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает статус задачи.
     *
     * @return Статус задачи.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Устанавливает статус задачи.
     *
     * @param status Статус задачи.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Возвращает приоритет задачи.
     *
     * @return Приоритет задачи.
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Устанавливает приоритет задачи.
     *
     * @param priority Приоритет задачи.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Возвращает email автора задачи.
     *
     * @return Email автора задачи.
     */
    public String getEmailAuthor() {
        return emailAuthor;
    }

    /**
     * Устанавливает email автора задачи.
     *
     * @param emailAuthor Email автора задачи.
     */
    public void setEmailAuthor(String emailAuthor) {
        this.emailAuthor = emailAuthor;
    }

    /**
     * Возвращает email исполнителя задачи.
     *
     * @return Email исполнителя задачи.
     */
    public String getEmailAssignee() {
        return emailAssignee;
    }

    /**
     * Устанавливает email исполнителя задачи.
     *
     * @param emailAssignee Email исполнителя задачи.
     */
    public void setEmailAssignee(String emailAssignee) {
        this.emailAssignee = emailAssignee;
    }

    /**
     * Возвращает список комментариев к задаче.
     *
     * @return Список комментариев к задаче.
     */
    public List<CommentTextFromDbDto> getComments() {
        return comments;
    }

    /**
     * Устанавливает список комментариев к задаче.
     *
     * @param comments Список комментариев к задаче.
     */
    public void setComments(List<CommentTextFromDbDto> comments) {
        this.comments = comments;
    }
}
