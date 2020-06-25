package com.example.mylibrarydemo.entry;

import java.util.Date;

public class BorrowDetail {
    private int borrowId;
    private String bname;
    private String userName;
    private Date btime;
    private Date deadline;
    private Date rtime;

    public BorrowDetail(int borrowId, String bname, String userName, Date btime, Date deadline, Date rtime) {
        this.borrowId = borrowId;
        this.bname = bname;
        this.userName = userName;
        this.btime = btime;
        this.deadline = deadline;
        this.rtime = rtime;
    }

    public BorrowDetail() {
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBtime() {
        return btime;
    }

    public void setBtime(Date btime) {
        this.btime = btime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getRtime() {
        return rtime;
    }

    public void setRtime(Date rtime) {
        this.rtime = rtime;
    }
}
