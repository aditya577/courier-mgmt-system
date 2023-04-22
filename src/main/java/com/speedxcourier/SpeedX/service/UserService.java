package com.speedxcourier.SpeedX.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.speedxcourier.SpeedX.domain.User;
import com.speedxcourier.SpeedX.domain.UserDetails;
import com.speedxcourier.SpeedX.repository.UserDetailsRepo;
import com.speedxcourier.SpeedX.repository.UserRepo;
import com.speedxcourier.SpeedX.security.UserManager;
import com.speedxcourier.SpeedX.util.SpeedXUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Autowired
    private UserManager userManager;

    private final String wrongCredsMsg = "Wrong Credentials";

    public String login(HttpServletRequest request) {
        log.info("viewLoginPage...");
        if (HttpMethod.GET.toString().equals(request.getMethod()))
            return "login";
        else if (HttpMethod.POST.toString().equals(request.getMethod()))
            return processLogin(request);
        return "redirect:/";
    }

    public String signup(HttpServletRequest request) {
        log.info("viewSignupPage...");
        if (HttpMethod.GET.toString().equals(request.getMethod()))
            return "signup";
        else if (HttpMethod.POST.toString().equals(request.getMethod()))
            return registerUser(request);
        return "redirect:/";
    }

    public String userHome(HttpServletRequest request) {
        log.info("viewUserHome...");
        if (userLoggedIn(request))
            return "user_home";
        return "redirect:/login";
    }

    public String logout(HttpServletRequest request) {
        log.info("logout...");
        String sessionUser = userManager.getLoggedInUser(request);
        if (SpeedXUtil.isEmpty(sessionUser))
            return "redirect:/login";
        request.getSession().invalidate();
        String msg = "Logged out successfully";
        log.debug(msg + " | user- " + sessionUser);
        HttpSession session = request.getSession();
        session.setAttribute(SpeedXUtil.MESSAGE, msg);
        session.setAttribute(SpeedXUtil.STATUS_COLOR, SpeedXUtil.COLOR_SUCCESS);
        return "redirect:/";
    }

    private String processLogin(HttpServletRequest request) {
        log.info("process_login...");
        String pass = request.getParameter(SpeedXUtil.PASSWORD);
        String username = request.getParameter(SpeedXUtil.USERNAME);
        log.debug("user: " + username + " | pass: " + pass);
        User user = userRepo.findUserByUsername(username);
        HttpSession session = request.getSession();

        // user does not exist
        boolean userIsNull = (user == null);
        boolean incorrectPassword = false;
        if(!userIsNull) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            incorrectPassword = !(passwordEncoder.matches(pass, user.getPassword()));
        }
        if (userIsNull || incorrectPassword) {
            session.setAttribute(SpeedXUtil.MESSAGE, wrongCredsMsg);
            session.setAttribute(SpeedXUtil.STATUS_COLOR, SpeedXUtil.COLOR_FAILURE);
            log.error(userIsNull ? ("user- " + username + " doesn't exist") : ("wrong password for user- " + username) );
            return "redirect:/login";
        }

        String msg = "Login successful";
        session.setAttribute(SpeedXUtil.MESSAGE, msg);
        session.setAttribute(SpeedXUtil.STATUS_COLOR, SpeedXUtil.COLOR_SUCCESS);
        session.setAttribute(SpeedXUtil.USERNAME, username);
        return "redirect:/user_home";
    }

    private String registerUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass = passwordEncoder.encode(request.getParameter(SpeedXUtil.PASSWORD));
        SimpleDateFormat sf = new SimpleDateFormat(SpeedXUtil.DATE_YYYYMMDD_HHMMSS);
        Date dt = null;
        try {
            dt = sf.parse(sf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String username = request.getParameter(SpeedXUtil.USERNAME);
        String role = request.getParameter("role");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        // check for existing username, email, phone
        User user = userRepo.findUserByUsername(username);
        if (!Objects.isNull(user)) {
            String msg = "username- " + username + " already taken";
            log.error(msg);
            session.setAttribute(SpeedXUtil.MESSAGE, msg);
            session.setAttribute(SpeedXUtil.STATUS_COLOR, SpeedXUtil.COLOR_FAILURE);
            return "redirect:/signup";
        }
        
        UserDetails userDetails = userDetailsRepo.findByEmailOrPhone(email, phone);
        if (!Objects.isNull(userDetails)) {
            String msg = "";
            if(email.equals(userDetails.getEmail()))
                msg = "email- " + email + " already taken";
            
            else if(phone.equals(userDetails.getPhone()))
                msg = "phone- " + phone + " already taken";
            
            log.error(msg);
            session.setAttribute(SpeedXUtil.MESSAGE, msg);
            session.setAttribute(SpeedXUtil.STATUS_COLOR, SpeedXUtil.COLOR_FAILURE);
            return "redirect:/signup";
        }

        user = User.builder()
                .username(username)
                .password(pass)
                .createdAt(dt)
                .build();

        userDetails = UserDetails.builder()
                        .user(user)
                        .role(role)
                        .name(name)
                        .phone(phone)
                        .email(email)
                        .createdAt(dt)
                        .updatedAt(dt)
                        .build();

        userRepo.save(user);
        userDetailsRepo.save(userDetails);
        String msg = "Registration successfully done";
        log.error(msg + " for user- " + username);
        session.setAttribute(SpeedXUtil.MESSAGE, msg);
        session.setAttribute(SpeedXUtil.STATUS_COLOR, SpeedXUtil.COLOR_SUCCESS);
        return "redirect:/login";

    }

    private boolean userLoggedIn(HttpServletRequest request) {
        String sessionUser = userManager.getLoggedInUser(request);
        if (SpeedXUtil.isEmpty(sessionUser)) return false;
        return true;
    }
}
