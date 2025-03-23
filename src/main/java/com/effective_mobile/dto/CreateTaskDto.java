package com.effective_mobile.dto;

import com.effective_mobile.enums.Priority;
import com.effective_mobile.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateTaskDto {

    private static final Logger logger = LoggerFactory.getLogger(CreateTaskDto.class);

    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private String emailAuthor;
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

    public String getEmailAuthor() {
        return emailAuthor;
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

    public void setEmailAuthor(String emailAuthor) {
        this.emailAuthor = emailAuthor;
    }

    public void setEmailAssignee(String emailAssignee) {
        this.emailAssignee = emailAssignee;
    }
}