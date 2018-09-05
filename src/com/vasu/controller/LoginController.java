package com.vasu.controller;

import com.vasu.dao.UserRepository;
import com.vasu.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/api/user/login", method = POST)
    @ResponseBody
    public String login(@RequestBody User user) {
        try {

            List<User> users = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
            if (users.size() > 0) {
                return  "User Logged in!!!";
            } else {
                throw new Exception();
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            return "Not a registered user!!!";
        }
    }
}
