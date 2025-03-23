package com.effective_mobile.dto;

import com.effective_mobile.enums.Priority;
import com.effective_mobile.enums.Status;

import java.util.List;

public class TaskAndCommentsForAssigneeFromDbDto {

    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private String emailAssignee;
    private List<String> comments;

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

    public TaskAndCommentsForAssigneeFromDbDto() {
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

    public String getEmailAssignee() {
        return emailAssignee;
    }

    public void setEmailAssignee(String emailAssignee) {
        this.emailAssignee = emailAssignee;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}