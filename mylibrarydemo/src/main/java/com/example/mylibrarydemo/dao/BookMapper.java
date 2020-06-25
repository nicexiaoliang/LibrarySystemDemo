package com.example.mylibrarydemo.dao;

import com.example.mylibrarydemo.entry.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {
    public List<BookList> getlist();

    public List<BookList> getListByBname(@Param("bname") String bname);

    public List<Book> getRes(@Param("ISBN") String ISBN);

    public Reservation getResId(@Param("book") Book book, @Param("user") User user);

    List<ReservationDetail> getResById(@Param("user") User user);

//    归还图书
    public void returnBookById(@Param("borrow") Borrow borrow);

    public List<BorrowDetail> getBorById(@Param("user") User user);

    public List<ReservationDetail> getReslist();

//    处理预约
    int processRes(@Param("isbn") String ISBN, @Param("user") User user);

    //    添加图书
    public void addBookList(@Param("booklist") BookList bookList, @Param("location") String location, @Param("state") Integer state);

    //    查询借阅情况
    public List<BorrowDetail> getBorlist();

    public List<ReservationDetail> getResDetail();

    public List<BorrowDetail> getBorDetail();

    void insertBorrow(@Param("rid")int reservationId, @Param("op") int operator);

    int deleteBookListById(@Param("isbn")String isbn);











}
