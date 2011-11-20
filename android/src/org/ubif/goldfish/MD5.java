package org.ubif.goldfish;

import java.security.*;
public class MD5 {
    public static String hexdigit(Object o) {
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("MD5");
            msgDigest.update(o.toString().getBytes());
            byte digest[] = msgDigest.digest();
            
            // Create Hex String
            StringBuffer hexStr = new StringBuffer();
            for (int i=0; i<digest.length; i++){
                hexStr.append(Integer.toHexString(0xFF&digest[i]));
            }
            return hexStr.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
