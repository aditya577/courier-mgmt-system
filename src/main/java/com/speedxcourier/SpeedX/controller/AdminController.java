package com.speedxcourier.SpeedX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speedxcourier.SpeedX.repository.GrievanceRepo;
import com.speedxcourier.SpeedX.service.GrievanceService;
import com.speedxcourier.SpeedX.service.PackageService;
import com.speedxcourier.SpeedX.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private GrievanceService grievanceService;

    @Autowired
    private PackageService packageService;

    @Autowired
    private UserService userService;
    
    @GetMapping("home")
    public String adminHome() {
        return "admin_home";
    }

    @GetMapping("get/users") 
    public String getAllUsers(HttpServletRequest request) {
        return userService.getAllUsers(request);
    }

    @GetMapping("get/packages")
    public String getAllPackages(HttpServletRequest request) {
        return packageService.getAllPackages(request);
    }

    @GetMapping("get/grievances")
    public String getAllGrievances(HttpServletRequest request) {
        return grievanceService.getAllGrievances(request);
    }
}
