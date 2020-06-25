package com.example.mylibrarydemo.entry;

import java.util.Date;

public class ReservationDetail {
    private int reservationId;
    private String bname;
    private int bookId;
    private Date deadline;
    private String userName;

    public ReservationDetail() {

    }

    public ReservationDetail(int reservationId, String bname, int bookId, Date deadline, String userName) {
        this.reservationId = reservationId;
        this.bname = bname;
        this.bookId = bookId;
        this.deadline = deadline;
        this.userName = userName;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "ReservationDetail{" +
                "reservationId=" + reservationId +
                ", bname='" + bname + '\'' +
                ", bookId=" + bookId +
                ", deadline=" + deadline +
                ", userName='" + userName + '\'' +
                '}';
    }
}
