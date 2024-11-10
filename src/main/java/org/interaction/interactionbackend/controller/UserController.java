package org.interaction.interactionbackend.controller;

import org.interaction.interactionbackend.enums.Role;
import org.interaction.interactionbackend.po.*;
import org.interaction.interactionbackend.service.UserService;
import org.interaction.interactionbackend.util.JwtUtil;
import org.interaction.interactionbackend.util.ResponseBuilder;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * login api
     * @param requestData <br>
     * {email: email, upass: password} <br>
     * @return <br>
     * {code: 0, msg: error_msg} or <br>
     * {code: 1, msg: success_msg, data: token} <br>
     */
    @PostMapping("/login")
    public ResponseVO login(@RequestBody Map<String, String> requestData) {
        String email = requestData.get("email");
        String password = requestData.get("upass");
        // check email
        if (checkEmailInValid(email)) {
            return ResponseBuilder.buildErrorResponse("邮箱格式不正确", null);
        }
        // login verification
        User user = userService.login(email, password);
        if (user != null) {
            return ResponseBuilder.buildSuccessResponse("登录成功", jwtUtil.generateToken(user));
        } else {
            return ResponseBuilder.buildErrorResponse("用户名或密码错误", null);
        }
    }

    /**
     * register api
     * @param requestData <br>
     * {email: email, <br>
     * upass: password, <br>
     * role: ADMIN | PHOTOGRAPHER} <br>
     * @return <br>
     * {code: 0, msg: error_msg} or <br>
     * {code: 1, msg: success_msg} <br>
     */
    @PostMapping("/add")
    public ResponseVO register(@RequestBody Map<String, String> requestData) {
        String email = requestData.get("email");
        String upass = requestData.get("upass");
        Role role = Role.valueOf(requestData.get("role"));
        // check email
        if (checkEmailInValid(email)) {
            return ResponseBuilder.buildErrorResponse("邮箱格式不正确", null);
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
}
