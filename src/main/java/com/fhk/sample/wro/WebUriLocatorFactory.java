package com.fhk.sample.wro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ro.isdc.wro.model.resource.locator.UriLocator;
import ro.isdc.wro.model.resource.locator.factory.AbstractUriLocatorFactory;

@Component
class WebUriLocatorFactory extends AbstractUriLocatorFactory 
{
	@SuppressWarnings("unused")
	private final static Logger loger = LoggerFactory.getLogger(WebUriLocatorFactory.class);
	
	private final ZipWebjarUriLocator webjarUriLocator;
	private final ClassPathWildcardUriLocator classPathWildcardUriLocator;
	private final NullUriLocator nullUriLocator;
	
	public WebUriLocatorFactory()
	{
		webjarUriLocator = new ZipWebjarUriLocator();
		classPathWildcardUriLocator = new ClassPathWildcardUriLocator();
		nullUriLocator = new NullUriLocator();
	}
	
	@Override
	public UriLocator getInstance(final String uri) 
	{
		final UriLocator[] locators = new UriLocator[]
		{
			webjarUriLocator,
			classPathWildcardUriLocator,
			nullUriLocator,
		};
		
		for(final UriLocator ul : locators)
		{
			if(ul.accept(uri))
			{
				return ul;
			}
		}
		return null;
	}

}
