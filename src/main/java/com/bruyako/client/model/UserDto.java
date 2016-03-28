package com.bruyako.client.model;

import java.io.Serializable;

/**
 * Created by brunyatko on 28.03.16.
 */
public class UserDto implements Serializable {
    private String login;
    private String password;

    public UserDto() {
    }

    public UserDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
