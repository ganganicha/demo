package com.example.demo.controler;

import com.example.demo.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthorizationService autherization;


    @GetMapping("/getAuthToken")
    void all() {
        autherization.getAuthToken();
    }


    @GetMapping("/isTokenExpired")
    void exp() {
        autherization.isJWTExpired();
    }

}
