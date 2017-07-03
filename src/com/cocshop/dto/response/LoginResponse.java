package com.cocshop.dto.response;

import com.cocshop.View.view;
import com.cocshop.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by Ken on 7/3/2017.
 */
public class LoginResponse {
    @JsonView({view.loginResponse.class})
    private int status;
    @JsonView({view.loginResponse.class})
    private String message;
    @JsonView({view.loginResponse.class})
    private UserDto user;

    public LoginResponse() {
    }

    public LoginResponse(int status, String message, UserDto user) {
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
