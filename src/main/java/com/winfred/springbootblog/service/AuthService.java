package com.winfred.springbootblog.service;

import com.winfred.springbootblog.payload.LoginDto;
import com.winfred.springbootblog.payload.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
