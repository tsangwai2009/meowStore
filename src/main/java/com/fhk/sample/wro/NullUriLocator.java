package com.fhk.sample.wro;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.input.NullInputStream;

import ro.isdc.wro.model.resource.locator.UriLocator;

class NullUriLocator implements UriLocator
{
	@Override
	public InputStream locate(final String uri) throws IOException 
	{
		return new NullInputStream(0L);
	}

	@Override
	public boolean accept(final String uri) 
	{
		return true;
	}

}
