package com.winfred.springbootblog.controller;

import com.winfred.springbootblog.payload.LoginDto;
import com.winfred.springbootblog.payload.RegisterDto;
import com.winfred.springbootblog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //login REST API
    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String response =authService.login(loginDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //register REST API
    @PostMapping(value = {"/register","signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response =authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}