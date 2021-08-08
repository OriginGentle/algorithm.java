package com.training.day05;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ycb
 * @since 2021/8/8-14:02
 */
public class Hash {

    private MessageDigest hash;

    public Hash(String algorithm) {
        try {
            hash = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String hashCode(String input) {
        return DatatypeConverter.printHexBinary(hash.digest(input.getBytes())).toUpperCase();
    }

}
