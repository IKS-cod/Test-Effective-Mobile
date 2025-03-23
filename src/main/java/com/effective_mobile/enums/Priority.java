package com.effective_mobile.enums;

public enum Priority {
    HIGH,
    MEDIUM,
    LOW;

    @Override
    public String toString() {
        return name();
    }
}