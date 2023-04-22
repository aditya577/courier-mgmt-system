package com.speedxcourier.SpeedX.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.speedxcourier.SpeedX.domain.User;
import com.speedxcourier.SpeedX.repository.UserRepo;
import com.speedxcourier.SpeedX.security.UserManager;
import com.speedxcourier.SpeedX.util.SpeedXUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserManager userManager;

    private final String wrongCredsMsg = "Wrong Credentials";

    @GetMapping("")
    public String viewHomePage() {
        log.info("viewHomePage...");
        return "index";
    }

    @RequestMapping("login")
    public String viewLoginPage(HttpServletRequest request) {
        log.info("viewLoginPage...");
        if (HttpMethod.GET.toString().equals(request.getMethod()))
            return "login";
        else if (HttpMethod.POST.toString().equals(request.getMethod()))
            return processLogin(request);
        return "redirect:/";
    }

    @RequestMapping("signup")
    public String viewSignupPage(HttpServletRequest request) {
        log.info("viewSignupPage...");
        if (HttpMethod.GET.toString().equals(request.getMethod()))
            return "signup";
        else if (HttpMethod.POST.toString().equals(request.getMethod()))
            return registerUser(request);
        return "redirect:/";
    }

    @GetMapping("logout")
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

    @GetMapping("user_home")
    public String viewUserHome(HttpServletRequest request) {
        log.info("viewUserHome...");
        if (userLoggedIn(request))
            return "user_home";
        return "redirect:/login";
    }

    public String processLogin(HttpServletRequest request) {
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

    public String registerUser(HttpServletRequest request) {
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
        User user = userRepo.findUserByUsername(username);
        if (!Objects.isNull(user)) {
            String msg = "username- " + username + " already taken";
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
        userRepo.save(user);
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
