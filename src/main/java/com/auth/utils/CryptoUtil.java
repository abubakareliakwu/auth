package com.auth.utils;


import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Random;

public class CryptoUtil {

    private static int workload = 12;

    public static final String bcrypt(final String s) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(s, salt);

        return(hashed_password);
    }


    public static boolean checkBcryptHash(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if(null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);


        return(password_verified);
    }


    public static String generateRandomPassword() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}