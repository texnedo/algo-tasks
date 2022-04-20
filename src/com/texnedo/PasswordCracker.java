package com.texnedo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PasswordCracker {
    private static final String WRONG_PASSWORD = "WRONG PASSWORD";

    public static String passwordCracker(List<String> passwords, String loginAttempt) {
        HashSet<String> existingPasswords = new HashSet<>();
        int minLength = Integer.MAX_VALUE;
        int maxLength = 0;
        for (String password : passwords) {
            existingPasswords.add(password);
            if (password.length() > maxLength) {
                maxLength = password.length();
            }
            if (password.length() < minLength) {
                minLength = password.length();
            }
        }
        return passwordCrackerInternal(
                existingPasswords, new HashMap<>(),
                minLength, maxLength, loginAttempt
        );
    }

    private static String passwordCrackerInternal(HashSet<String> passwords,
                                                  HashMap<String, String> existing,
                                                  int minLength,
                                                  int maxLength,
                                                  String loginAttempt) {
        String result = existing.get(loginAttempt);
        if (result != null) {
            return result;
        }
        for (int i = minLength; i < loginAttempt.length() + 1 && i <= maxLength; i++) {
            String token = loginAttempt.substring(0, i);
            if (passwords.contains(token)) {
                if (token.length() == loginAttempt.length()) {
                    existing.put(loginAttempt, token);
                    return token;
                }
                String restPart = passwordCrackerInternal(
                        passwords,
                        existing,
                        minLength,
                        maxLength,
                        loginAttempt.substring(i)
                );
                if (!WRONG_PASSWORD.equals(restPart)) {
                    String resultSum = String.format("%s %s", token, restPart);
                    existing.put(loginAttempt, resultSum);
                    return resultSum;
                }
            }
        }
        return WRONG_PASSWORD;
    }

    public static void main(String[] args) {
        List<String> passwords = Arrays.asList("because", "can", "do", "must", "we", "what");
        String attempt = "wedowhatwemustbecausewecan";
        System.out.println(passwordCracker(passwords, attempt));
    }
}
