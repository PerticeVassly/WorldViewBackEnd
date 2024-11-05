package org.interaction.interactionbackend.controller;

import org.interaction.interactionbackend.po.*;
import org.interaction.interactionbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * login api
     * @param data {email: email, upass: password}
     * @return {code: 0, msg: error_msg} or
     *         {code: 1, msg: success_msg, data: [userId]}
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> data) {
        String email = data.get("email");
        String password = data.get("upass");
        Map<String, Object> response = new HashMap<>();

        // check email
        if (!checkEmail(email)) {
            response.put("code", 0);
            response.put("msg", "邮箱格式不正确");
            return response;
        }

        // login verification
        User user = userService.login(email, password);
        if (user != null) {
            response.put("code", 1);  // 登录成功
            response.put("msg", "登录成功");
            response.put("data", new Object[]{user.getId()});
        } else {
            response.put("code", 0);  // 登录失败
            response.put("msg", "用户名或密码错误");
        }
        return response;
    }

    /**
     * register api
     * @param data {email: email, upass: password, tempcode: tempcode}
     * @return {code: 0, msg: error_msg} means failure or
     *         {code: 1} means success
     */
    @PostMapping("/add")
    public Map<String, Object> register(@RequestBody Map<String, String> data) {
        String email = data.get("email");
        String upass = data.get("upass");
        String tempcode = data.get("tempcode");
        Map<String, Object> response = new HashMap<>();

        // check email
        if (!checkEmail(email)) {
            response.put("code", 0);
            response.put("msg", "邮箱格式不正确");
            return response;
        }

        //login in
        boolean success = userService.register(email, upass, tempcode);
        if (success) {
            response.put("code", 1);
        } else {
            response.put("code", 0);
            response.put("msg", "邮箱已存在");
        }
        return response;
    }

    boolean checkEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
