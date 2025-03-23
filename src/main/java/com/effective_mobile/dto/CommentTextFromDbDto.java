package com.effective_mobile.dto;

/**
 * Класс, представляющий текст комментария, полученный из базы данных.
 * Используется для передачи только текста комментария, без дополнительной информации.
 *
 */
public class CommentTextFromDbDto {

    /**
     * Текст комментария.
     */
    private String text;

    /**
     * Конструктор для создания объекта CommentTextFromDbDto с заданным текстом комментария.
     *
     * @param text текст комментария
     */
    public CommentTextFromDbDto(String text) {
        this.text = text;
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
}
