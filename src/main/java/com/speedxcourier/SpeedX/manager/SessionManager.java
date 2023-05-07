package com.speedxcourier.SpeedX.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.speedxcourier.SpeedX.domain.User;
import com.speedxcourier.SpeedX.repository.UserRepo;
import com.speedxcourier.SpeedX.util.SpeedXUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SessionManager {

    @Autowired
    private UserRepo userRepo;
    
    public String getLoggedInUsername(HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            String username = (String) session.getAttribute(SpeedXUtil.USERNAME);
            return username;
        } catch(Exception e) {
            log.debug("error in getting logged-in-user", e);
        }
        return null;
    }

    public User getLoggedInUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            String username = (String) session.getAttribute(SpeedXUtil.USERNAME);
            return userRepo.findUserByUsername(username);
        } catch(Exception e) {
            log.debug("error in getting logged-in-user", e);
        }
        return null;
    }

    public boolean userLoggedIn(HttpServletRequest request) {
        String sessionUser = getLoggedInUsername(request);
        if (SpeedXUtil.isEmpty(sessionUser)) return false;
        return true;
    }
}
