package com.speedxcourier.SpeedX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speedxcourier.SpeedX.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String home() {
        return "index";
    }

    @RequestMapping("login")
    public String login(HttpServletRequest request) {
        return userService.login(request);
    }

    @RequestMapping("signup")
    public String signup(HttpServletRequest request) {
        return userService.signup(request);
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        return userService.logout(request);
    }

    @GetMapping("user_home")
    public String userHome(HttpServletRequest request) {
        return userService.userHome(request);
    }
}
