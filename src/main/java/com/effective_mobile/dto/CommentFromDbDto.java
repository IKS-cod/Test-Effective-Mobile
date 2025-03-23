package com.effective_mobile.dto;

/**
 * Класс, представляющий комментарий, полученный из базы данных.
 * Используется для передачи информации о комментарии, включая его идентификатор, текст и название задачи, к которой он относится.
 *
 */
public class CommentFromDbDto {

    /**
     * Идентификатор комментария.
     */
    private Long id;

    /**
     * Текст комментария.
     */
    private String text;

    /**
     * Название задачи, к которой относится комментарий.
     */
    private String titleTask;

    /**
     * Возвращает идентификатор комментария.
     *
     * @return идентификатор комментария
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор комментария.
     *
     * @param id идентификатор комментария
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает текст комментария.
     *
     * @return текст комментария
     */
    public String getText() {
        return text;
    }

    /**
     * Устанавливает текст комментария.
     *
     * @param text текст комментария
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Возвращает название задачи, к которой относится комментарий.
     *
     * @return название задачи
     */
    public String getTitleTask() {
        return titleTask;
    }

    /**
     * Устанавливает название задачи, к которой относится комментарий.
     *
     * @param titleTask название задачи
     */
    public void setTitleTask(String titleTask) {
        this.titleTask = titleTask;
    }
}
