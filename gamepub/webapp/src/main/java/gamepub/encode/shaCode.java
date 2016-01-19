/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamepub.encode;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;

/**
 *
 * @author fitok
 */
public class shaCode {
    
    public static String code(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        String result="";
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(str.getBytes("UTF-8"));
        byte[] digest = md.digest();
        result = String.format("%064x", new BigInteger(1,digest));
        return result;
    }
}
