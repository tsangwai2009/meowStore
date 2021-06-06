package com.fhk.sample;

public class SampleException extends Exception
{
	private static final long serialVersionUID = -1751212777552261218L;
	
	public SampleException() 
	{
		
	}
	
	public SampleException(final String message) {
		super(message);
	}

	public SampleException(final Throwable cause) {
		super(cause);
	}

	public SampleException(final String message, final Throwable cause) 
	{
		super(message, cause);
	}
}
