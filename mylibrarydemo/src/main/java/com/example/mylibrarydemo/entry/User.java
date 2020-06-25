package com.example.mylibrarydemo.entry;

public class User {
    private int userId;
    private String userName;
    private String passWd;
    private String rname;
    private String phone;
    private String email;

    public User(String userName,String passWd) {
        this.userName=userName;
        this.passWd=passWd;
    }

    public User(int userId, String userName, String passWd, String rname, String phone, String email) {
        this.userId = userId;
        this.userName = userName;
        this.passWd = passWd;
        this.rname = rname;
        this.phone = phone;
        this.email = email;
    }

    public User(int userId, String passWd, String userName) {
        this.userId=userId;
        this.userName=userName;
        this.passWd=passWd;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", passWd='" + passWd + '\'' +
                ", rname='" + rname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
