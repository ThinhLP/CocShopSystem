package com.cocshop.dto;

/**
 * Created by Ken on 7/5/2017.
 */
public class ErrorDto {
    private int code;
    private String[] messages;

    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
