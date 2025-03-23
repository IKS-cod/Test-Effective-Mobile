package com.effective_mobile.dto;

/**
 * Класс, представляющий данные для создания или обновления комментария.
 * Используется для передачи текста комментария от клиента к серверу.
 *
 */
public class CreateOrUpdateCommentsDto {

    /**
     * Текст комментария.
     */
    private String text;

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
