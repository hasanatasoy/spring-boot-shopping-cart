package com.hasanatasoy.shoppingcart.authentication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;

public class Crypt {

    public static String encode(String toBeEncoded){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(toBeEncoded);
    }

    public static boolean isMatched(String raw, String targetEncoded){
        return new BCryptPasswordEncoder().matches(raw, targetEncoded);
    }

    public static String encodeBase64(String toBeEncoded) {
        return Base64.getEncoder().encodeToString(toBeEncoded.getBytes());
    }

    public static String decodeBase64(String toBeDecoded){
        byte[] decodedByteArray = Base64.getDecoder().decode(toBeDecoded.getBytes());
        return new String(decodedByteArray);
    }

    public static String generateSecureCodeWith(String toBeEncoded){
        return encode(toBeEncoded);
    }

}
