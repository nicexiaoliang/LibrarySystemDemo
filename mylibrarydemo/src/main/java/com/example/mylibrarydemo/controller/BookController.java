package com.example.mylibrarydemo.controller;

import com.example.mylibrarydemo.entry.*;
import com.example.mylibrarydemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/managebooks")
public class BookController {
    @Autowired
    public BookService bookService;

    @GetMapping("/booklist")
    public String getBookList(Model model, HttpServletRequest request) {
        User user =(User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        List<BookList> list = bookService.getlist();
        model.addAttribute("list", list);
        return "user_booklist";

    }

    @GetMapping(value = "/reservation")
    public String getReslistById(Model model, HttpServletRequest request) {
        User user =(User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        System.out.println(user);
        List<ReservationDetail> reslist = bookService.getResById(user);
        System.out.println("拿到的结果"+reslist.toString());
        model.addAttribute("list", reslist);
        return "user_reservation";
    }

    //    管理员进入添加图书页面
    @GetMapping("/admin/books")
    public String addBooklist(Model model, HttpServletRequest request) {
        User user =(User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        List<BookList> list = bookService.getlist();
        model.addAttribute("list", list);
        return "admin_addbook";
    }

    //    处理用户预约
    @GetMapping(value = "/{ISBN}/booklist")
    public String processRes( Model model,
                              HttpServletRequest request,
                              @PathVariable(value = "ISBN") String ISBN,
                              HttpServletResponse response) {

        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        response.setContentType("text/html;charset=utf8");

        ReservationResult<Reservation> rr;
        PrintWriter pw = null;
        Reservation r = null;

        try {
            pw = response.getWriter();
            int i = bookService.processRes(ISBN, user);
            rr = new ReservationResult<>(true, r);
        }catch (Exception e){
            System.out.println(e);
            System.out.println("发生异常，预约失败");
            rr=new ReservationResult<>(false,"预约失败");

        }

        if (rr.isSuccess()) {
            pw.print("<script>alert('预约成功');window.location.href='/managebooks/booklist';</script>");
        } else {
            pw.print("<script>alert('预约失败,请重新预约!');window.location.href='/managebooks/booklist';</script>");
        }
        List<BookList> list = bookService.getlist();
        model.addAttribute("list", list);
        return "user_booklist";
    }


    //    管理员真正添加图书
    @RequestMapping(value = "/admin/books",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ProcessResult addBook(HttpServletRequest request, @RequestBody BookList bookList) {
        User user =(User) request.getSession().getAttribute("user");
        bookList.setOperator(user.getUserId());
        bookService.addBooklist(bookList,0);
        ProcessResult processResult=new ProcessResult();
        processResult.setStatus(true);
        return processResult;
    }


    //    管理员查看所有预约
    @GetMapping(value = "/admin/reservation")
    public String getReslist(HttpServletRequest request,Model model) {
        User user =(User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        List<ReservationDetail> reslist = bookService.getReslist();
        for (ReservationDetail reservationDetail : reslist) {
            System.out.println(reservationDetail);
        }

        model.addAttribute("list", reslist);
        return "admin_processreservation";

    }

    //    管理员查看借阅情况
    @GetMapping(
            value = "/admin/borrow")
    public String getBorrowList(Model model,HttpServletRequest request) {
        User user =(User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        List<BorrowDetail> borlist = bookService.getBorlist();
        model.addAttribute("list", borlist);
        return "admin_borrow";
    }


    //    管理员同意申请图书
    @GetMapping(
            value = "/admin/{reservationId}/borrow")
    public String insertBor(Model model,HttpServletRequest request,@PathVariable(value = "reservationId") Integer reservatioId) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        bookService.insertBorrow(reservatioId,user.getUserId());
        List<ReservationDetail> reslist = bookService.getReslist();
        model.addAttribute("list", reslist);
        return "admin_processreservation";
    }


//    用户查看借阅记录/退订图书
@GetMapping(value = "/borrow")
public String listBorListById(Model model,
                              HttpServletRequest request) {
    User user = (User) request.getSession().getAttribute("user");
    model.addAttribute("user", user);
    List<BorrowDetail> list = bookService.getBorByUId(user);
    model.addAttribute("list", list);
    return "user_borrow";
}


    @GetMapping(value = "{borrowId}/return")
    public String returnBook(Model model, HttpServletRequest request,@PathVariable(value = "borrowId") Integer borrowId) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        Borrow borrow = new Borrow();
        borrow.setBorrowId(borrowId);
        bookService.returnBook(borrow);
        List<BorrowDetail> borrowDetails = bookService.getBorByUId(user);
        model.addAttribute("list", borrowDetails);
        return "user_borrow";
    }


}
