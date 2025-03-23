package com.effective_mobile.dto;

import com.effective_mobile.enums.Status;

/**
 * Класс, представляющий данные для обновления статуса задачи.
 * Используется для передачи информации о новом статусе задачи от клиента к серверу.
 *
 */
public class StatusTaskDto {

    /**
     * Новый статус задачи.
     */
    private Status status;

    /**
     * Возвращает новый статус задачи.
     *
     * @return новый статус задачи
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Устанавливает новый статус задачи.
     *
     * @param status новый статус задачи
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
