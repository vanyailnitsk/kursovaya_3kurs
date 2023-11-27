package com.example.java_lab4.service;

import com.example.java_lab4.service.db.UserRepository;

public class LoginService {
    private final UserRepository userRepository;

    public LoginService() {
        this.userRepository = new UserRepository();
    }

    public int auth(String login, String password) {
        return userRepository.getUserIdByUsernameAndPassword(login,password);
    }
}
