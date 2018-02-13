package by.loiko.library.util;

import org.apache.commons.codec.digest.DigestUtils;


/***
 Author: Aliaksei Loika
 Date: 12.02.2018
 ***/
public class PasswordEncoder {
    /** The Constant SALT. */
    private static final String SALT = "MySaltFor_Password195?#@";

    /**
     * Encode password.
     *
     * @param password the password
     * @return the string
     */
    public static String encodePassword(String password) {
        return DigestUtils.md5Hex(password + SALT);
    }
}
