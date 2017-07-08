package com.cocshop.dto;

/**
 * Created by Ken on 7/5/2017.
 */
public class RegisterResponse {
    private int code;
    private String[] messages;

    public RegisterResponse() {
    }

    public RegisterResponse(int code, String[] messages) {
        this.code = code;
        this.messages = messages;
    }

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
