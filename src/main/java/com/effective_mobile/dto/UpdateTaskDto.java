package com.effective_mobile.dto;

import com.effective_mobile.enums.Priority;
import com.effective_mobile.enums.Status;

public class UpdateTaskDto {

    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private String emailAssignee;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getEmailAssignee() {
        return emailAssignee;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setEmailAssignee(String emailAssignee) {
        this.emailAssignee = emailAssignee;
    }
}