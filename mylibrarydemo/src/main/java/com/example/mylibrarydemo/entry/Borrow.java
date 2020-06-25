package com.example.mylibrarydemo.entry;

import java.util.Date;

public class Borrow {
    private int borrowId;
    private int bookId;
    private int userId;
    private Date btime;
    private Date deadline;
    private Date rtime;
    private int operator;

    public Borrow() {
    }

    public Borrow(int borrowId, int bookId, int userId, Date btime, Date deadline, Date rtime, int operator) {
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.userId = userId;
        this.btime = btime;
        this.deadline = deadline;
        this.rtime = rtime;
        this.operator = operator;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }
}
