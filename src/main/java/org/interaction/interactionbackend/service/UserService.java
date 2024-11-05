package org.interaction.interactionbackend.service;

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

    public boolean register(String email, String password, String tempcode) {
        // check email has existed
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return false;
        }

        //save user
        userRepository.save(new User(email, password));
        return true;
    }
}
