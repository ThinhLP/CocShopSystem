package com.cocshop.dto;

import com.cocshop.View.view;
import com.cocshop.model.TblRole;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by Ken on 7/3/2017.
 */
public class UserDto {
    @JsonView(view.loginResponse.class)
    private int userId;
    @JsonView(view.loginResponse.class)
    private String username;
    @JsonView(view.loginResponse.class)
    private String email;
    @JsonView(view.loginResponse.class)
    private String firstname;
    @JsonView(view.loginResponse.class)
    private String lastname;
    @JsonView(view.loginResponse.class)
    private String birthdate;
    @JsonView(view.loginResponse.class)
    private int role;

    public UserDto() {
    }

    public UserDto(int userId, String username, String email, String firstname, String lastname, String birthdate, int role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
