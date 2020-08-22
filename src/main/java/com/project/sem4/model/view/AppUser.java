package com.project.sem4.model.view;

public class AppUser {
    private Integer userId;
    private String email;
    private String passWord;

    public AppUser(Integer userId, String email, String passWord) {
        this.userId = userId;
        this.email = email;
        this.passWord = passWord;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
