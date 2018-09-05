package com.vasu.controller;

import com.vasu.dao.UserRepository;
import com.vasu.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@Slf4j
public class SignupController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/api/user/signup", method = POST)
    @ResponseBody
    public String create(@RequestBody User user) {
        log.info(user.toString());
        try {
             userRepository.save(user);
             return "You have signedup successfully!!!!";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "You are already registered with us!!! Please try to login!!";
        }
    }

}
