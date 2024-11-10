package org.interaction.interactionbackend.service;

import org.interaction.interactionbackend.enums.Role;
import org.interaction.interactionbackend.util.ResponseBuilder;
import org.interaction.interactionbackend.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.interaction.interactionbackend.po.User;
import org.interaction.interactionbackend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public ResponseVO register(String email, String password, Role role) {
        // check email has existed
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return ResponseBuilder.buildErrorResponse("邮箱已存在", null);
        }
        //save user
        userRepository.save(new User(email, password, role));
        return ResponseBuilder.buildSuccessResponse("注册成功", null);
    }
}
