package com.effective_mobile.model;

import com.effective_mobile.enums.Priority;
import com.effective_mobile.enums.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность, представляющая задание в системе.
 *
 * @author [Ваше имя]
 */
@Entity
public class Task {

    /**
     * Уникальный идентификатор задания.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название задания.
     */
    private String title;

    /**
     * Описание задания.
     */
    private String description;

    /**
     * Статус задания.
     */
    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * Приоритет задания.
     */
    @Enumerated(EnumType.STRING)
    private Priority priority;

    /**
     * Автор задания.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private UserInfo author;

    /**
     * Исполнитель задания.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private UserInfo assignee;

    /**
     * Список комментариев к заданию.
     */
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Comments> comments = new ArrayList<>();

    /**
     * Пустой конструктор для использования в JPA.
     */
    public Task() {
    }

    /**
     * Конструктор для создания задания с заданными параметрами.
     *
     * @param id идентификатор задания
     * @param title название задания
     * @param description описание задания
     * @param status статус задания
     * @param priority приоритет задания
     * @param author автор задания
     * @param assignee исполнитель задания
     * @param comments список комментариев к заданию
     */
    public Task(Long id, String title, String description, Status status, Priority priority,
                UserInfo author, UserInfo assignee, List<Comments> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.author = author;
        this.assignee = assignee;
        this.comments = comments;
    }

    /**
     * Возвращает идентификатор задания.
     *
     * @return идентификатор
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор задания.
     *
     * @param id идентификатор
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает название задания.
     *
     * @return название
     */
    public String getTitle() {
        return title;
    }

    /**
     * Устанавливает название задания.
     *
     * @param title название
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Возвращает описание задания.
     *
     * @return описание
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание задания.
     *
     * @param description описание
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает статус задания.
     *
     * @return статус
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Устанавливает статус задания.
     *
     * @param status статус
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Возвращает приоритет задания.
     *
     * @return приоритет
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Устанавливает приоритет задания.
     *
     * @param priority приоритет
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Возвращает автора задания.
     *
     * @return автор
     */
    public UserInfo getAuthor() {
        return author;
    }

    /**
     * Устанавливает автора задания.
     *
     * @param author автор
     */
    public void setAuthor(UserInfo author) {
        this.author = author;
    }

    /**
     * Возвращает исполнителя задания.
     *
     * @return исполнитель
     */
    public UserInfo getAssignee() {
        return assignee;
    }

    /**
     * Устанавливает исполнителя задания.
     *
     * @param assignee исполнитель
     */
    public void setAssignee(UserInfo assignee) {
        this.assignee = assignee;
    }

    /**
     * Возвращает список комментариев к заданию.
     *
     * @return список комментариев
     */
    public List<Comments> getComments() {
        return comments;
    }

    /**
     * Устанавливает список комментариев к заданию.
     *
     * @param comments список комментариев
     */
    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    /**
     * Возвращает строковое представление задания.
     *
     * @return строковое представление
     */
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", author=" + author +
                ", assignee=" + assignee +
                '}';
    }
}
