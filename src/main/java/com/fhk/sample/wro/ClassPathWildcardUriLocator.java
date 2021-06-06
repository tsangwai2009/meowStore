package com.fhk.sample.wro;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import ro.isdc.wro.model.resource.locator.UriLocator;

class ClassPathWildcardUriLocator implements UriLocator
{
	private final static Logger logger = LoggerFactory.getLogger(ClassPathWildcardUriLocator.class);
	private final static class ResourceComparator implements Comparator<Resource>
	{
		@Override
		public int compare(Resource o1, Resource o2) 
		{
			final String s1 = o1.toString();
			final String s2 = o2.toString();
			int ret = s1.split("/").length - s2.split("/").length;
			if(ret==0)
			{
				ret = o1.getFilename().split("-").length - o2.getFilename().split("-").length;
			}
			if(ret==0)
			{
				ret = s1.compareTo(s2);
			}
			return ret;
		}
		
	}
	
	
	private final PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver;
	private final ResourceComparator resourceComparator;
	
	ClassPathWildcardUriLocator()
	{
		pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
		resourceComparator = new ResourceComparator();
	}
	
	@Override
	public InputStream locate(final String uri) throws IOException 
	{
		ByteArrayOutputStream baos = null;
		try
		{
			if(logger.isTraceEnabled())
			{
				logger.trace("URI:" + uri);
			}
			
			baos = new ByteArrayOutputStream();
			final Resource[] res = pathMatchingResourcePatternResolver.getResources(uri);
			final TreeSet<Resource> set = new TreeSet<Resource>(resourceComparator);
			set.addAll(Arrays.asList(res));
			for(final Resource r : set)
			{
				if(logger.isTraceEnabled())
				{
					logger.trace("\t" + r.toString());
				}
				IOUtils.copy(r.getInputStream(), baos);
			}
			
		}finally
		{
			IOUtils.closeQuietly(baos);
		}
		return new BufferedInputStream(new ByteArrayInputStream(baos.toByteArray()));
	}

	@Override
	public boolean accept(final String uri) 
	{
		return true;
	}

}
