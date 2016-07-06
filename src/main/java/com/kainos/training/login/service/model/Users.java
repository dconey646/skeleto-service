package com.kainos.training.login.service.model;

/**
 * Created by thomasf on 04/07/2016.
 */
public class Users {

    private String username;
    private String password;

    public Users(String username, String password) {
        setUsername(username);
        setPassword(password);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
