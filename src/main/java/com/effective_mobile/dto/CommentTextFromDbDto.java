package com.effective_mobile.dto;

public class CommentTextFromDbDto {

    private String text;

    public CommentTextFromDbDto(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}