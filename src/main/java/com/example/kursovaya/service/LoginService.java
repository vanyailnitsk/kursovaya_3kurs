package com.example.kursovaya.service;

import com.example.kursovaya.service.db.UserRepository;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginService {
    private final UserRepository userRepository;

    public LoginService() {
        this.userRepository = new UserRepository();
    }

    public int auth(String login, String password) {
        String hashedPassword = hashPassword(password);
        return userRepository.getUserIdByUsernameAndPassword(login,hashedPassword);
    }
    public int register(String name,String login,String password) {
        if (userRepository.isUserExistsByLogin(login)) {
            return -1;
        }
        String hashedPassword = hashPassword(password);
        return userRepository.createUser(name,login,hashedPassword);
    }
    public String hashPassword(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        BigInteger hashInt = new BigInteger(1, hashBytes);
        return hashInt.toString(16);
    }
}
