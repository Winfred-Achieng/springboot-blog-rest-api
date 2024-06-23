package com.winfred.springbootblog.controller;

import com.winfred.springbootblog.payload.JwtAuthResponse;
import com.winfred.springbootblog.payload.LoginDto;
import com.winfred.springbootblog.payload.RegisterDto;
import com.winfred.springbootblog.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag( name ="CRUD REST APIs for Auth Resources " )

public class AuthController {

    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @Operation( summary = "Login REST API",
            description = "Login REST API is used to login a user")

    @ApiResponse(responseCode = "200",
            description = "Http Status 200 SUCCESS")

    //login REST API
    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
        String token =authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }


    @Operation( summary = "Register REST API",
            description = "Register REST API is used to add a user into database")

    @ApiResponse(responseCode = "201",
            description = "Http Status 201 CREATED")
    //register REST API
    @PostMapping(value = {"/register","signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response =authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
