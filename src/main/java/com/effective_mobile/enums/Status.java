package com.effective_mobile.enums;

public enum Status {
    PENDING,
    IN_PROGRESS,
    COMPLETED;

    @Override
    public String toString() {
        return name();
    }
}