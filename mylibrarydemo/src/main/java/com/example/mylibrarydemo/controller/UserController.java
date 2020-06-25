package com.example.mylibrarydemo.controller;

import com.example.mylibrarydemo.dao.UserMapper;
import com.example.mylibrarydemo.entry.User;
import com.example.mylibrarydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/managebooks")
public class UserController {
    @Autowired
    public UserService userService;
//    public UserMapper userMapper;
//    @RequestMapping("/testusermapper")
//@ResponseBody
//    public User testuserMapper() {
//        return userMapper.checkManager(new User("admit_ff","001212"));
//    }


    @GetMapping(value = "/login")
    public String login() {
        System.out.println("hahahhah");
        return "login";
    }

    @GetMapping(value = "/detail")
    public String detail(Model model, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        if (username == null) {
            return "login";
        }
        User user = new User(username, password);
        if (username.contains("admit_")) {
            User user1 = userService.checkManager(user);
            if (user1 == null) {
                return "login";
            }

            model.addAttribute("user", user1);
            httpServletRequest.getSession().setAttribute("user", user1);
            return "detail_admin";
        } else {
            User user2 = userService.checkUser(user);
            if (user2 == null) {
                return "login";
            }
            model.addAttribute("user", user2);
            System.out.println(user2);
            httpServletRequest.getSession().setAttribute("user",user2);
            return "detail_user";
        }


    }


    @GetMapping(value = "admin/homepage")
    public String adminhomepage(Model model,
                                HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        return "detail_admin";
    }


    @GetMapping(value = "/homepage")
    public String homepage(Model model,
                           HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        return "detail_user";
    }
}
