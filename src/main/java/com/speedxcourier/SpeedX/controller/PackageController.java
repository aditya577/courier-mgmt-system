package com.speedxcourier.SpeedX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speedxcourier.SpeedX.service.PackageService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/package/")
public class PackageController {
    
    @Autowired
    private PackageService packageService;

    @RequestMapping("book")
    public String bookPackage(HttpServletRequest request) {
        return packageService.bookPackage(request);
    }

    @RequestMapping("user")
    public String getUserPackages(HttpServletRequest request) {
        return packageService.getUserPackage(request);
    }

}
