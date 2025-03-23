package com.effective_mobile.model;


import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность, представляющая комментарий к заданию.
 *
 * @author [Ваше имя]
 */
@Entity
public class Comments {

    /**
     * Уникальный идентификатор комментария.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Текст комментария.
     */
    private String text;

    /**
     * Задание, к которому относится комментарий.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    /**
     * Пустой конструктор для использования в JPA.
     */
    public Comments() {
    }

    /**
     * Конструктор для создания комментария с заданными параметрами.
     *
     * @param id идентификатор комментария
     * @param text текст комментария
     * @param task задание, к которому относится комментарий
     */
    public Comments(Long id, String text, Task task) {
        this.id = id;
        this.text = text;
        this.task = task;
    }

    /**
     * Возвращает идентификатор комментария.
     *
     * @return идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор комментария.
     *
     * @param id идентификатор
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает текст комментария.
     *
     * @return текст
     */
    public String getText() {
        return text;
    }

    /**
     * Устанавливает текст комментария.
     *
     * @param text текст
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Возвращает задание, к которому относится комментарий.
     *
     * @return задание
     */
    public Task getTask() {
        return task;
    }

    /**
     * Устанавливает задание, к которому относится комментарий.
     *
     * @param task задание
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * Проверяет равенство текущего комментария с другим объектом.
     *
     * @param o объект для сравнения
     * @return true, если объекты равны, false иначе
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return Objects.equals(id, comments.id) && Objects.equals(text, comments.text);
    }

    /**
     * Возвращает хэш-код комментария.
     *
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }

    /**
     * Возвращает строковое представление комментария.
     *
     * @return строковое представление
     */
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
