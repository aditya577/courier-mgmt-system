package com.speedxcourier.SpeedX.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.speedxcourier.SpeedX.domain.Package;
import com.speedxcourier.SpeedX.domain.PackageHistory;
import com.speedxcourier.SpeedX.domain.User;
import com.speedxcourier.SpeedX.domain.UserDetails;
import com.speedxcourier.SpeedX.manager.SessionManager;
import com.speedxcourier.SpeedX.repository.PackageHistoryRepo;
import com.speedxcourier.SpeedX.repository.PackageRepo;
import com.speedxcourier.SpeedX.repository.UserDetailsRepo;
import com.speedxcourier.SpeedX.util.SpeedXUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PackageService {
    
    @Autowired
    private PackageRepo packageRepo;

    @Autowired
    private PackageHistoryRepo packageHistoryRepo;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Autowired
    private SessionManager sessionManager;

    public String bookPackage(HttpServletRequest request) {
        log.info("bookPackage...");
        if (HttpMethod.GET.toString().equals(request.getMethod()))
            return "book_package_form";
        else if (HttpMethod.POST.toString().equals(request.getMethod()))
            return processPackageBooking(request);
        return "redirect:/index";
    }

    public String getUserPackage(HttpServletRequest request) {
        User sessionUser = sessionManager.getLoggedInUser(request);
        if(sessionUser == null) {
            return "redirect:/login";
        }
        List<Package> list = packageRepo.getPackagesByUserId(sessionUser.getId());
        request.getSession().setAttribute("userPackages", list);
        return "user_packages_table";

    }

    public String getAllPackages(HttpServletRequest request) {
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
        List<Package> list = packageRepo.findAll();
        request.getSession().setAttribute("userPackages", list);
        return "all_packages_table";
    }

    private String processPackageBooking(HttpServletRequest request) {
        User sessionUser = sessionManager.getLoggedInUser(request);
        if(sessionUser == null) {
            return "redirect:/login";
        }

        String type = request.getParameter("type");
        String sourceCity = StringUtils.capitalize(request.getParameter("sourceCity"));
        String destinationCity = StringUtils.capitalize(request.getParameter("destinationCity"));
        Date dt = SpeedXUtil.getFormattedDate(new Date());
        BigDecimal price = new BigDecimal(11);

        Package package_ = Package.builder()
                .user(sessionUser)
                .packageType(type)
                .sourceCity(sourceCity)
                .destinationCity(destinationCity)
                .calculatedPrice(price)
                .packageStatus("Booked")
                .createdAt(dt)
                .updatedAt(dt)
                .build();
        
        PackageHistory history = PackageHistory.builder()
                .package_(package_)
                .bookEvent("booked")
                .eventFrom(sourceCity)
                .eventTo(destinationCity)
                .createdAt(dt)
                .build();
        
        try {
            packageRepo.save(package_);
            packageHistoryRepo.save(history);
            String msg = "Package booking successful ("+sourceCity+" -> "+destinationCity+" | charge: " + price + ")";
            log.info(msg + " for user- " + sessionUser.getUsername());
            request.getSession().setAttribute(SpeedXUtil.MESSAGE, msg);
            request.getSession().setAttribute(SpeedXUtil.STATUS_COLOR, SpeedXUtil.COLOR_SUCCESS);
        } catch (Exception e) {
            String msg = "Package booking failed ("+sourceCity+" -> "+destinationCity+")";
            log.error(msg + " for user- " + sessionUser.getUsername());
            request.getSession().setAttribute(SpeedXUtil.MESSAGE, msg);
            request.getSession().setAttribute(SpeedXUtil.STATUS_COLOR, SpeedXUtil.COLOR_FAILURE);
        }
        return "redirect:/user_home";
    }
}
