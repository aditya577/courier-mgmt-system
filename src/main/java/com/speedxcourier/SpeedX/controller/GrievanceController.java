package com.speedxcourier.SpeedX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speedxcourier.SpeedX.service.GrievanceService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/grievance/")
public class GrievanceController {
    
    @Autowired
    private GrievanceService grievanceService;

    @RequestMapping("file")
    public String fileGrievance(HttpServletRequest request) {
        return grievanceService.fileGrievance(request);
    }

    @RequestMapping("user")
    public String getUserGrievances(HttpServletRequest request) {
        return grievanceService.getUserPackage(request);
    }
}
