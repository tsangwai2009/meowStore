package com.fhk.sample.util;

import com.fhk.sample.SampleException;


public class UtilException extends SampleException
{
	private static final long serialVersionUID = 5481234547976139551L;
	public UtilException() 
	{
		
	}
	
	public UtilException(final String message) {
		super(message);
	}

	public UtilException(final Throwable cause) {
		super(cause);
	}

	public UtilException(final String message, final Throwable cause) 
	{
		super(message, cause);
	}
}
