package com.winfred.springbootblog.service;

import com.winfred.springbootblog.payload.LoginDto;

public interface AuthService {

    String login(LoginDto loginDto);
}
