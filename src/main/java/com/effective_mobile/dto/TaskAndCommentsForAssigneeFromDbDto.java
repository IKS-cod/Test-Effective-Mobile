package com.effective_mobile.dto;

import com.effective_mobile.enums.Priority;
import com.effective_mobile.enums.Status;

import java.util.List;

/**
 * Класс, представляющий задачу и список комментариев для исполнителя, полученные из базы данных.
 * Используется для передачи информации о задаче и ее комментариях, связанных с исполнителем.
 *
 */
public class TaskAndCommentsForAssigneeFromDbDto {

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
     * Email исполнителя задачи.
     */
    private String emailAssignee;

    /**
     * Список комментариев к задаче (только текст).
     */
    private List<String> comments;

    /**
     * Конструктор для создания объекта TaskAndCommentsForAssigneeFromDbDto с заданными параметрами.
     *
     * @param id идентификатор задачи
     * @param title название задачи
     * @param description описание задачи
     * @param status статус задачи
     * @param priority приоритет задачи
     * @param emailAssignee email исполнителя задачи
     * @param comments список комментариев к задаче
     */
    public TaskAndCommentsForAssigneeFromDbDto(Long id,
                                               String title,
                                               String description,
                                               Status status,
                                               Priority priority,
                                               String emailAssignee,
                                               List<String> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.emailAssignee = emailAssignee;
        this.comments = comments;
    }

    /**
     * Пустой конструктор для использования в JPA или других фреймворках.
     */
    public TaskAndCommentsForAssigneeFromDbDto() {
    }

    /**
     * Возвращает идентификатор задачи.
     *
     * @return идентификатор задачи
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор задачи.
     *
     * @param id идентификатор задачи
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает название задачи.
     *
     * @return название задачи
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает название задачи.
     *
     * @param title название задачи
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Возвращает описание задачи.
     *
     * @return описание задачи
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание задачи.
     *
     * @param description описание задачи
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает статус задачи.
     *
     * @return статус задачи
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Устанавливает статус задачи.
     *
     * @param status статус задачи
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Возвращает приоритет задачи.
     *
     * @return приоритет задачи
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Устанавливает приоритет задачи.
     *
     * @param priority приоритет задачи
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Возвращает email исполнителя задачи.
     *
     * @return email исполнителя задачи
     */
    public String getEmailAssignee() {
        return emailAssignee;
    }

    /**
     * Устанавливает email исполнителя задачи.
     *
     * @param emailAssignee email исполнителя задачи
     */
    public void setEmailAssignee(String emailAssignee) {
        this.emailAssignee = emailAssignee;
    }

    /**
     * Возвращает список комментариев к задаче.
     *
     * @return список комментариев к задаче
     */
    public List<String> getComments() {
        return comments;
    }

    /**
     * Устанавливает список комментариев к задаче.
     *
     * @param comments список комментариев к задаче
     */
    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
