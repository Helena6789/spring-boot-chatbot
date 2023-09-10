package com.hq.springbootchatbot.controller;

import com.hq.springbootchatbot.model.User;
import com.hq.springbootchatbot.model.UserRole;
import com.hq.springbootchatbot.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public void addGuest(@RequestBody User user) {
        registerService.add(user, UserRole.ROLE_NORMAL);
    }

}
