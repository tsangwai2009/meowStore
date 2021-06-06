package com.fhk.sample.wro;

import java.util.Properties;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ro.isdc.wro.config.jmx.ConfigConstants;
import ro.isdc.wro.http.ConfigurableWroFilter;
import ro.isdc.wro.manager.factory.WroManagerFactory;

@Configuration
@EnableConfigurationProperties(Wro4jProperties.class)
class Wro4jConfig 
{
	@Inject
    private Wro4jProperties wro4jProperties;

    @Bean
    ConfigurableWroFilter wroFilter(final WroManagerFactory wroManagerFactory) 
    {
        final ConfigurableWroFilter cwf = new ConfigurableWroFilter();
        cwf.setProperties(wroFilterProperties());
        cwf.setWroManagerFactory(wroManagerFactory);
        return cwf;
    }

    private Properties wroFilterProperties() 
    {
    	final Properties properties = new Properties();
    	
        properties.put(ConfigConstants.debug.name(), String.valueOf(wro4jProperties.isDebug()));
        properties.put(ConfigConstants.disableCache.name(), String.valueOf(wro4jProperties.isDisableCache()));
        properties.put(ConfigConstants.cacheUpdatePeriod.name(), String.valueOf(wro4jProperties.getCacheUpdatePeriod()));
        properties.put(ConfigConstants.resourceWatcherUpdatePeriod.name(), String.valueOf(wro4jProperties.getResourceWatcherUpdatePeriod()));
        properties.put(ConfigConstants.cacheGzippedContent.name(), String.valueOf(wro4jProperties.isCacheGzippedContent()));
        properties.put(ConfigConstants.parallelPreprocessing.name(), String.valueOf(wro4jProperties.isParallelProcessing()));
        properties.put(ConfigConstants.minimizeEnabled.name(), "false");
        
        return properties;
    }

    @Bean
    @ConditionalOnClass(javax.servlet.DispatcherType.class)
    FilterRegistrationBean wro4jFilterRegistration(final ConfigurableWroFilter wroFilter) 
    {
        final FilterRegistrationBean fr = new FilterRegistrationBean(wroFilter);
        fr.addUrlPatterns(wro4jProperties.getUrlPattern());
        return fr;
    }

    @Bean
    WroManagerFactory fallbackWroManagerFactory() 
    {
        return new DynamicWroManagerFactory();
    }

}
