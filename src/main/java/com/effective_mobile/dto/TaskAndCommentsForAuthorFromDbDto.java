package com.effective_mobile.dto;

import com.effective_mobile.enums.Priority;
import com.effective_mobile.enums.Status;

import java.util.List;

public class TaskAndCommentsForAuthorFromDbDto {

    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private String emailAuthor;
    private List<String> comments;

    public TaskAndCommentsForAuthorFromDbDto(Long id,
                                             String title,
                                             String description,
                                             Status status,
                                             Priority priority,
                                             String emailAuthor,
                                             List<String> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.emailAuthor = emailAuthor;
        this.comments = comments;

    }

    public TaskAndCommentsForAuthorFromDbDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getEmailAuthor() {
        return emailAuthor;
    }

    public void setEmailAuthor(String emailAuthor) {
        this.emailAuthor = emailAuthor;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}