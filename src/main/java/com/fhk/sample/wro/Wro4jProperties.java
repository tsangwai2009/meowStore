package com.fhk.sample.wro;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wro4j")
class Wro4jProperties 
{
    private boolean disableCache;

    private boolean debug = false;

    private int cacheUpdatePeriod = 0;

    private int resourceWatcherUpdatePeriod = 5;

    private String urlPattern = "/resources/*";

    private boolean cacheGzippedContent = true;
    
    private boolean parallelProcessing = false;
    
    public boolean isDisableCache() {
        return disableCache;
    }

    public void setDisableCache(final boolean disableCache) {
        this.disableCache = disableCache;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(final boolean debug) {
        this.debug = debug;
    }

    public int getCacheUpdatePeriod() {
        return cacheUpdatePeriod;
    }

    public void setCacheUpdatePeriod(final int cacheUpdatePeriod) {
        this.cacheUpdatePeriod = cacheUpdatePeriod;
    }

    public int getResourceWatcherUpdatePeriod() {
        return resourceWatcherUpdatePeriod;
    }

    public void setResourceWatcherUpdatePeriod(final int resourceWatcherUpdatePeriod) {
        this.resourceWatcherUpdatePeriod = resourceWatcherUpdatePeriod;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(final String urlPattern) {
        this.urlPattern = urlPattern;
    }

	public boolean isCacheGzippedContent() {
		return cacheGzippedContent;
	}

	public void setCacheGzippedContent(final boolean cacheGzippedContent) {
		this.cacheGzippedContent = cacheGzippedContent;
	}

	public boolean isParallelProcessing() {
		return parallelProcessing;
	}

	public void setParallelProcessing(final boolean parallelProcessing) {
		this.parallelProcessing = parallelProcessing;
	}
}
