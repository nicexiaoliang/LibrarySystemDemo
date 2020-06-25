package com.example.mylibrarydemo.entry;

import java.util.Date;

public class Reservation {
    private int reservationId;
    private int userId;
    private int bookId;
    private Date deadline;

    public Reservation(int reservationId, int userId, int bookId, Date deadline) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.bookId = bookId;
        this.deadline = deadline;
    }

    public Reservation() {
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
