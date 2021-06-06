package com.fhk.sample.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Util 
{
	public static Date setZeroTime(final Date d)
	{
		final Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		for(final int p : new int[] {Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND})
		{
			cal.set(p, cal.getMinimum(p));
		}
		return cal.getTime();
	}
	
	public static Date setMaxTime(final Date d)
	{
		final Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		for(final int p : new int[] {Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND})
		{
			cal.set(p, cal.getMaximum(p));
		}
		return cal.getTime();
	}
	
	
	
    public static String convertToHex(final byte[] data) 
    {
        final StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
        	int halfbyte = (data[i] >>> 4) & 0x0F;
        	int two_halfs = 0;
        	do {
	            if ((0 <= halfbyte) && (halfbyte <= 9))
	                buf.append((char) ('0' + halfbyte));
	            else
	            	buf.append((char) ('a' + (halfbyte - 10)));
	            halfbyte = data[i] & 0x0F;
        	} while(two_halfs++ < 1);
        }
        return buf.toString();
    }
    
	public static String getUniqueSignature() 
    {
    	final String q = UUID.randomUUID().toString();
    	try
    	{
    		return SHA1.hash(q);	
    	}catch(final Exception e)
    	{
    		return q;
    	}
    }
	
	public static String getUniqueSignature(final String seed)
	{
    	try
    	{
    		return SHA1.hash(seed);	
    	}catch(final Exception e)
    	{
    		return getUniqueSignature();
    	}
	}
    
    public static String getStackTrace(final Throwable aThrowable) 
    {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		return result.toString();
    }
    
    public static <T> T[][] split(final T[] values, final int size, final T[][] stub)
    {
    	final List<T[]> ret = new ArrayList<T[]>();
		if(values.length > size)
		{
			
			for(int i=0;i<values.length;i+=size)
			{
				final int index = i + size;
				final T[] parts = Arrays.copyOfRange(values, i, (index>=values.length) ? values.length : index);
				ret.add(parts);
			}
		}else
		{
			ret.add(values);
		}
		return ret.toArray(stub);
    }
    
    public static void main(final String[] args)
    {
    	final Calendar toCalendar = Calendar.getInstance();
		final Calendar todayCalendar = Calendar.getInstance();
		
		todayCalendar.set(Calendar.MONTH, 0);
		todayCalendar.set(Calendar.DAY_OF_MONTH,1);
		todayCalendar.set(Calendar.HOUR_OF_DAY, 0);
		todayCalendar.set(Calendar.MINUTE, 0);	
		todayCalendar.set(Calendar.SECOND, 0);
		todayCalendar.set(Calendar.MILLISECOND, 0);
		
		toCalendar.setTime(todayCalendar.getTime());
		toCalendar.add(Calendar.YEAR, 1);
		toCalendar.add(Calendar.MILLISECOND, -1);
		
		System.out.println(todayCalendar.getTime().toString());
		System.out.println(toCalendar.getTime().toString());
    }
    
    
}
