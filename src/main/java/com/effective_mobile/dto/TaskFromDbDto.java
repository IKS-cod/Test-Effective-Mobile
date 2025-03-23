package com.effective_mobile.dto;

import com.effective_mobile.enums.Priority;
import com.effective_mobile.enums.Status;

/**
 * Класс, представляющий задачу, полученную из базы данных.
 * Используется для передачи информации о задаче от сервера к клиенту.
 *
 */
public class TaskFromDbDto {

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
     * Возвращает email автора задачи.
     *
     * @return email автора задачи
     */
    public String getEmailAuthor() {
        return emailAuthor;
    }

    /**
     * Устанавливает email автора задачи.
     *
     * @param emailAuthor email автора задачи
     */
    public void setEmailAuthor(String emailAuthor) {
        this.emailAuthor = emailAuthor;
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
}
