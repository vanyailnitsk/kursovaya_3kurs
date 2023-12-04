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
    public int register(String name,String login,String password) {
        if (userRepository.isUserExistsByLogin(login)) {
            return -1;
        }
        return userRepository.createUser(name,login,password);
    }
}
