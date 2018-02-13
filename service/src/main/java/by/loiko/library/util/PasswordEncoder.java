package by.loiko.library.util;

import org.apache.commons.codec.digest.DigestUtils;

/***
 Author: Aliaksei Loika
 Date: 12.02.2018
 ***/
public class PasswordEncoder {
    private static final String SALT = "MySaltFor_Password195?#@";

    public static String encodePassword(String password) {
        return DigestUtils.md5Hex(password + SALT);
    }
}
