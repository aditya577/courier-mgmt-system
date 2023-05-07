package com.speedxcourier.SpeedX.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.speedxcourier.SpeedX.domain.Grievance;
import com.speedxcourier.SpeedX.domain.GrievanceHistory;
import com.speedxcourier.SpeedX.domain.User;
import com.speedxcourier.SpeedX.domain.UserDetails;
import com.speedxcourier.SpeedX.manager.SessionManager;
import com.speedxcourier.SpeedX.repository.GrievanceHistoryRepo;
import com.speedxcourier.SpeedX.repository.GrievanceRepo;
import com.speedxcourier.SpeedX.repository.UserDetailsRepo;
import com.speedxcourier.SpeedX.util.SpeedXUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GrievanceService {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private GrievanceRepo grievanceRepo;

    @Autowired
    private GrievanceHistoryRepo grievanceHistoryRepo;

    @Autowired
    private UserDetailsRepo userDetailsRepo;
    
    public String fileGrievance(HttpServletRequest request) {
        log.info("fileGrievance...");
        if (HttpMethod.GET.toString().equals(request.getMethod()))
            return "file_grievance_form";
        else if (HttpMethod.POST.toString().equals(request.getMethod()))
            return processGrievanceFiling(request);
        return "redirect:/index";
    }

    public String getUserPackage(HttpServletRequest request) {
        User sessionUser = sessionManager.getLoggedInUser(request);
        if(sessionUser == null) {
            return "redirect:/login";
        }
        List<Grievance> list = grievanceRepo.getGrievancesByReporteeId(sessionUser.getId());
        request.getSession().setAttribute("userGrievances", list);
        return "user_grievance_table";
    }

    public String getAllGrievances(HttpServletRequest request) {
        User sessionUser = sessionManager.getLoggedInUser(request);
        if(sessionUser == null) {
            return "redirect:/login";
        }
        UserDetails userDetails = userDetailsRepo.findByUserId(sessionUser.getId());
        if(userDetails == null || !userDetails.getRole().equals(UserDetails.UserRole.ADMIN.toString())) {
            String msg = "User not authorized";
            request.getSession().setAttribute(SpeedXUtil.MESSAGE, msg);
            request.getSession().setAttribute(SpeedXUtil.STATUS_COLOR, SpeedXUtil.COLOR_FAILURE);
            return "redirect:/login";
        }
        List<Grievance> list = grievanceRepo.findAll();
        request.getSession().setAttribute("allGrievances", list);
        return "all_grievance_table";
    }

    private String processGrievanceFiling(HttpServletRequest request) {
        User sessionUser = sessionManager.getLoggedInUser(request);
        if(sessionUser == null) {
            return "redirect:/login";
        }

        String content = request.getParameter("content");
        Date dt = SpeedXUtil.getFormattedDate(new Date());

        Grievance grievance = Grievance.builder()
                    .content(content)
                    .reporteeId(sessionUser)
                    .createdAt(dt)
                    .updatedAt(dt)
                    .grievanceStatus("Filed")
                    .build();
        
        GrievanceHistory grievanceHistory = GrievanceHistory.builder()
                            .grievance(grievance)
                            .grievanceEvent("Created")
                            .createdAt(dt)
                            .build();
        
        
        try {
            grievanceRepo.save(grievance);
            grievanceHistoryRepo.save(grievanceHistory);
            String msg = "Grievance filed successfully ";
            log.info(msg + " for user- " + sessionUser.getUsername());
            request.getSession().setAttribute(SpeedXUtil.MESSAGE, msg);
            request.getSession().setAttribute(SpeedXUtil.STATUS_COLOR, SpeedXUtil.COLOR_SUCCESS);
        } catch (Exception e) {
            String msg = "Grievance filing failed ";
            log.error(msg + " for user- " + sessionUser.getUsername());
            request.getSession().setAttribute(SpeedXUtil.MESSAGE, msg);
            request.getSession().setAttribute(SpeedXUtil.STATUS_COLOR, SpeedXUtil.COLOR_FAILURE);
        }
        return "redirect:/user_home";
    }
}
