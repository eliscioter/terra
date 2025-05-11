package com.eliscioter.terra.commons.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Util {
    public static String hashPassword(String password) {
        Argon2 argon2 = Argon2Factory.create();
        try {
            return argon2.hash(10, 65536, 1, password.toCharArray());
        } finally {
            argon2.wipeArray(password.toCharArray());
        }
    }

    public static boolean verifyPassword(String hashed, String password) {
        Argon2 argon2 = Argon2Factory.create();
        try {
            return argon2.verify(hashed, password.toCharArray());
        } finally {
            argon2.wipeArray(password.toCharArray());
        }
    }
}
