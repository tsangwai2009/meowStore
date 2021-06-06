package com.fhk.sample.wro;

import static java.lang.String.format;
import static org.apache.commons.lang3.Validate.notNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webjars.WebJarAssetLocator;
import org.webjars.urlprotocols.UrlProtocolHandler;

import com.fhk.sample.util.Util;

import ro.isdc.wro.extensions.locator.WebjarUriLocator;
import ro.isdc.wro.model.resource.locator.ClasspathUriLocator;
import ro.isdc.wro.model.resource.locator.UriLocator;
import ro.isdc.wro.model.resource.locator.wildcard.DefaultWildcardStreamLocator;

/**
 * This is the wrapper class for the default WebJarAssetLocator
 * But it supports weird weblogic for changing the URL path from zip to jar:file 
 * @author wilson.lam
 *
 */
class ZipWebjarUriLocator implements UriLocator 
{
	private static final Logger logger = LoggerFactory.getLogger(WebjarUriLocator.class);

	public static final String ALIAS = "webjar";
	public static final String PREFIX = format("%s:", ALIAS);
	private final UriLocator classpathLocator = new ClasspathUriLocator();
	private final WebJarAssetLocator webjarAssetLocator = newWebJarAssetLocator();

	private static Set<URL> listParentURLsWithResource(final ClassLoader[] classLoaders, final String resource) {
		final Set<URL> urls = new HashSet<URL>();
		for (final ClassLoader classLoader : classLoaders) {
			try {
				final Enumeration<URL> enumeration = classLoader.getResources(resource);
				while (enumeration.hasMoreElements()) {
					urls.add(enumeration.nextElement());
				}
			} catch (final IOException e) {
				throw new RuntimeException(e);
			}
		}
		return urls;
	}

	private static Set<String> getAssetPaths(final Pattern filterExpr, final ClassLoader... classLoaders) {
		final Set<String> assetPaths = new HashSet<String>();
		final Set<URL> urls = listParentURLsWithResource(classLoaders, WebJarAssetLocator.WEBJARS_PATH_PREFIX);

		final ServiceLoader<UrlProtocolHandler> urlProtocolHandlers = ServiceLoader.load(UrlProtocolHandler.class);

		for (final URL url : urls) 
		{
			for (final UrlProtocolHandler urlProtocolHandler : urlProtocolHandlers) 
			{
				URL newURL;
				try 
				{
					newURL = new URL(url.toString().replace("zip","jar:file"));
					if (urlProtocolHandler.accepts(newURL.getProtocol()))
					{
						assetPaths.addAll(urlProtocolHandler.getAssetPaths(newURL, filterExpr, classLoaders));
					}else
					{
					}
				} catch (final MalformedURLException ex) 
				{
					logger.warn(ex.getMessage());
					logger.debug(Util.getStackTrace(ex));
				}
			}
		}

		return assetPaths;
	}

	private static String reversePath(final String assetPath) 
	{
		final String[] assetPathComponents = assetPath.split("/");
		final StringBuilder reversedAssetPath = new StringBuilder();
		for (int i = assetPathComponents.length - 1; i >= 0; --i) {
			if (reversedAssetPath.length() > 0) {
				reversedAssetPath.append('/');
			}
			reversedAssetPath.append(assetPathComponents[i]);
		}
		return reversedAssetPath.toString();
	}

	public static SortedMap<String, String> getFullPathIndex(final Pattern filterExpr, final ClassLoader... classLoaders) {

		final Set<String> assetPaths = getAssetPaths(filterExpr, classLoaders);

		final SortedMap<String, String> assetPathIndex = new TreeMap<String, String>();
		for (final String assetPath : assetPaths) {
			assetPathIndex.put(reversePath(assetPath), assetPath);
		}

		return assetPathIndex;
	}

	private WebJarAssetLocator newWebJarAssetLocator() 
	{
		return new WebJarAssetLocator(getFullPathIndex(Pattern.compile(".*"), Thread.currentThread().getContextClassLoader()));
	}

	public static String createUri(final String path) {
		notNull(path);
		return PREFIX + path;
	}

	@Override
	public InputStream locate(final String uri) throws IOException 
	{
		if(logger.isDebugEnabled())
		{
			logger.debug("locating: {}", uri);
		}
		
		try {
			final String fullpath = webjarAssetLocator.getFullPath(extractPath(uri));
			return classpathLocator.locate(ClasspathUriLocator.createUri(fullpath));
		} catch (final Exception e) {
			throw new IOException("No webjar with uri: " + uri + " available.", e);
		}
	}

	/**
	 * Replaces the protocol specific prefix and removes the query path if it
	 * exist, since it should not be accepted.
	 */
	private String extractPath(final String uri) {
		return DefaultWildcardStreamLocator.stripQueryPath(uri.replace(PREFIX, ""));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean accept(final String uri) {
		return uri.trim().startsWith(PREFIX);
	}

}
