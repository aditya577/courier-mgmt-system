package com.speedxcourier.SpeedX.security;

import org.springframework.stereotype.Component;

import com.speedxcourier.SpeedX.util.SpeedXUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserManager {
    
    public String getLoggedInUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            String username = (String) session.getAttribute(SpeedXUtil.USERNAME);
            return username;
        } catch(Exception e) {
            log.debug("error in getting logged-in-user", e);
        }
        return null;
    }
}
