package com.uber.Controller;

import com.uber.Model.User;
import com.uber.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {



    @Autowired
    private UserService user_service;



    @PostMapping("/newmoviebooking")
    public User bookingmovieticket(@RequestBody User user){

        User newuser = user_service.bookingMovie(user);

        return user_service.bookingMovie(newuser);

    }
}
