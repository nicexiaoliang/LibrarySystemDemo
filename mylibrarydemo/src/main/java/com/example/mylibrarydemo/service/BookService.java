package com.example.mylibrarydemo.service;

import com.example.mylibrarydemo.entry.*;

import java.util.List;

public interface BookService {
    public List<BookList> getlist();

    public List<ReservationDetail> getResById(User user);

    public int processRes(String isbn, User user);

    public void addBooklist(BookList bookList, int state);

    public List<ReservationDetail> getReslist();

    public List<BorrowDetail> getBorlist();

    public void insertBorrow(int rid, int op);

    public List<BorrowDetail> getBorByUId(User user);

    public void returnBook(Borrow borrow);
}
