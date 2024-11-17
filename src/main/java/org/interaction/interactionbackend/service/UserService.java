package org.interaction.interactionbackend.service;

import org.interaction.interactionbackend.enums.Role;
import org.interaction.interactionbackend.util.ResponseBuilder;
import org.interaction.interactionbackend.util.TokenUtil;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.interaction.interactionbackend.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.interaction.interactionbackend.po.User;
import org.interaction.interactionbackend.repository.UserRepository;
import org.interaction.interactionbackend.exception.WorldViewException;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenUtil tokenUtil;

    public ResponseVO login(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            throw WorldViewException.pwdWrong();
        }
        // login success and return token
        return ResponseBuilder.buildSuccessResponse("登录成功", tokenUtil.getToken(user));
    }

    public ResponseVO register(String email, String password, Role role) {
        // check email has existed
        User user = userRepository.findByEmail(email);
        if (user != null) {
            throw WorldViewException.emailExist();
        }
        //save user
        userRepository.save(new User(email, password, role));
        return ResponseBuilder.buildSuccessResponse("注册成功", null);
    }

    public ResponseVO getUserInfo(User user) {
        return ResponseBuilder.buildSuccessResponse("获取成功",
                Collections.singletonList(new UserVO(user)));
    }

    public ResponseVO resetUserInfo(UserVO newUserInfo, User user) {
        user.resetInfo(newUserInfo);
        userRepository.save(user);
        return ResponseBuilder.buildSuccessResponse("修改成功", null);
    }

    public ResponseVO resetUserPwd(User user, String oldPwd, String newPwd, String renewPwd) {
       if (newPwd.equals(renewPwd)) {
           if (user.getPassword().equals(oldPwd)) {
               user.setPassword(newPwd);
               userRepository.save(user);
               return ResponseBuilder.buildSuccessResponse("修改成功", null);
           } else {
               throw WorldViewException.pwdWrong();
           }
       } else {
           throw WorldViewException.pwdNotSame();
       }
    }
}
