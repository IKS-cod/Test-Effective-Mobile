package com.effective_mobile.dto;

import com.effective_mobile.enums.Status;

public class UpdateTaskStatusDto {

    private String title;
    private Status status;

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}