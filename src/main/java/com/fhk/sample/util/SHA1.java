package com.fhk.sample.util;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SHA1 
{
    public static String hash(final String text) throws UtilException
    {
    	try
    	{ 
    		final MessageDigest md = MessageDigest.getInstance("SHA-1");
    		md.update(text.getBytes("iso-8859-1"), 0, text.length());
    		return Util.convertToHex(md.digest());
    		
    	}catch(final Exception e)
    	{
    		throw new UtilException(e);
    	}
    }
    
    public static String hash(final String[] text) throws UtilException
    {
    	try
    	{ 
    		Arrays.sort(text,new Comparator<String>() {

    			@Override
				public int compare(final String s1, final String s2) {
					return s1.compareTo(s2);
				}
    			
    		});
    		final StringBuilder sb = new StringBuilder();
    		
    		final MessageDigest md = MessageDigest.getInstance("SHA-1");
    		for(final String s : text)
    		{
    			sb.append(s);
    		}
    		md.update(sb.toString().getBytes());
    		return Util.convertToHex(md.digest());
    		
    	}catch(final Exception e)
    	{
    		throw new UtilException(e);
    	}
    }
    
    public static String hash(final List<String> text) throws UtilException
    {
    	return hash(text.toArray(new String[0]));
    }
}
