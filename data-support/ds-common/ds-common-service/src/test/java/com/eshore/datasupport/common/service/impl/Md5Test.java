package com.eshore.datasupport.common.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;

public class Md5Test {

	@Test
	public void main() throws NoSuchAlgorithmException{
//		String a = Md5Cryption.encrypt("12345678");
//		String a = Md5Crypt.
//		System.out.println(a);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update("12345678".getBytes());
        byte [] b = md.digest();
        String a = byte2str(b);
        System.out.println(a);

        
        md.update(a.getBytes());
        byte [] bb = md.digest();
        String aa = byte2str(bb);
        System.out.println(aa);
	}

	static char[] hex = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'}; 
	
    private static String byte2str(byte []bytes){
        int len = bytes.length;   
        StringBuilder result = new StringBuilder();    
        for (int i = 0; i < len; i++) {   
            byte byte0 = bytes[i];   
            result.append(hex[byte0 >>> 4 & 0xf]);   
            result.append(hex[byte0 & 0xf]);   
        }
        return result.toString();
    }
}
