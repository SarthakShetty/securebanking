package com.group12.utils;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

public class ConvertStringtoSHA {
	
	public String sha1(String input) {
	    String sha1 = null;
	    try {
	        MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
	        msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
	        sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
	    } catch (Exception ex) {
	        throw new RuntimeException(ex);
	    }
	    return sha1;
	}

}
