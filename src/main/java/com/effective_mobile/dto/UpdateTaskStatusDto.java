package com.effective_mobile.dto;

import com.effective_mobile.enums.Status;

/**
 * Класс, представляющий данные для обновления статуса задачи и (возможно) ее названия.
 * Используется для передачи информации о новом статусе и, при необходимости, новом названии задачи от клиента к серверу.
 *
 */
public class UpdateTaskStatusDto {

    /**
     * Название задачи (может быть обновлено).
     */
    private String title;

    /**
     * Новый статус задачи.
     */
    private Status status;

    /**
     * Возвращает название задачи.
     *
     * @return название задачи
     */
    public String getTitle() {
        return title;
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
     * Устанавливает название задачи.
     *
     * @param title название задачи
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Устанавливает статус задачи.
     *
     * @param status статус задачи
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
