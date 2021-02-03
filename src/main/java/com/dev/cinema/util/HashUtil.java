package com.dev.cinema.util;

import com.dev.cinema.exceptions.DataProcessingException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class HashUtil {
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";

    public static String getHash(String password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try {
            StringBuilder hashedPassword = new StringBuilder();
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] hash = secretKeyFactory.generateSecret(spec).getEncoded();
            for (byte b : hash) {
                hashedPassword.append(String.format("%02x", b));
            }
            return hashedPassword.toString();
        } catch (GeneralSecurityException e) {
            throw new DataProcessingException("Cannot hashed password " + password
                    + " by algorithm " + ALGORITHM, e);
        }
    }

    public static byte[] getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }
}
