package com.example.mylibrarydemo.service;

import com.example.mylibrarydemo.dao.BookMapper;
import com.example.mylibrarydemo.entry.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImple implements BookService {

    @Autowired
    public BookMapper bookMapper;
    @Override
    public List<BookList> getlist() {
        return bookMapper.getlist();
    }

    @Override
    public List<ReservationDetail> getResById(User user) {
        return bookMapper.getResById(user);
    }

    @Override
    public int processRes(String isbn, User user) {
        return bookMapper.processRes(isbn,user);
    }

    @Override
    public void addBooklist(BookList bookList, int state) {
        this.bookMapper.addBookList(bookList,"图书流通室",state);
    }

    @Override
    public List<ReservationDetail> getReslist() {
        return bookMapper.getReslist();
    }

    @Override
    public List<BorrowDetail> getBorlist() {
        return bookMapper.getBorlist();
    }

    @Override
    public void insertBorrow(int rid, int op) {
         bookMapper.insertBorrow(rid,op);
    }

    @Override
    public List<BorrowDetail> getBorByUId(User user) {
        return bookMapper.getBorById(user);
    }

    @Override
    public void returnBook(Borrow borrow) {
        bookMapper.returnBookById(borrow);
    }
}
