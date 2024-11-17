package org.interaction.interactionbackend.controller;

import org.interaction.interactionbackend.enums.Role;
import org.interaction.interactionbackend.exception.WorldViewException;
import org.interaction.interactionbackend.po.*;
import org.interaction.interactionbackend.service.UserService;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.interaction.interactionbackend.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/login")
    public ResponseVO login(@RequestBody Map<String, String> requestData) {
        String email = requestData.get("email");
        String password = requestData.get("upass");
        // check email
        if (checkEmailInValid(email)) {
            throw WorldViewException.emailFormatWrong();
        }
        // login verification
        return userService.login(email, password);
    }

    @PostMapping("/add")
    public ResponseVO register(@RequestBody Map<String, String> requestData) {
        String email = requestData.get("email");
        String upass = requestData.get("upass");
        Role role = Role.valueOf(requestData.get("role"));
        // check email
        if (checkEmailInValid(email)) {
            throw WorldViewException.emailFormatWrong();
        }
        //login in
        return userService.register(email, upass, role);
    }

    boolean checkEmailInValid(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();
    }

    @PostMapping("/info")
    public ResponseVO getUserInfo(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return userService.getUserInfo(currentUser);
    }

    @PostMapping("/resetInfo")
    public ResponseVO resetUserInfo(@RequestBody UserVO newUserInfo,
                                    HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        return userService.resetUserInfo(newUserInfo, currentUser);
    }

    @PostMapping("/resetPwd")
    public ResponseVO resetPwd(@RequestBody Map<String, String> requestData,
                               HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        String oldPwd = requestData.get("oldPwd");
        String newPwd = requestData.get("newPwd");
        String renewPwd = requestData.get("renewPwd");
        return userService.resetUserPwd(currentUser, oldPwd, newPwd, renewPwd);
    }
}
