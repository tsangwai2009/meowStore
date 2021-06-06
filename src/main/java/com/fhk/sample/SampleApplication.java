package com.fhk.sample;

import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.fhk.sample.domain.dao")
@EnableAutoConfiguration(exclude={
		GroovyTemplateAutoConfiguration.class, 
		GsonAutoConfiguration.class, 
		WebSocketAutoConfiguration.class,
		JmxAutoConfiguration.class,
		
})
@EntityScan("com.fhk.sample.domain.entity")
@ComponentScan("com.fhk")
public class SampleApplication extends SpringBootServletInitializer
{
	private final static Logger logger = LoggerFactory.getLogger(SampleApplication.class);
	
    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) 
    {
        return application.sources(SampleApplication.class);
    }
    
    public static void main(final String[] args) throws IOException 
	{
		final ApplicationContext ctx = SpringApplication.run(SampleApplication.class, args);
		if(logger.isDebugEnabled())
		{
			final String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (final String beanName : beanNames) 
			{
				logger.debug(beanName);
			}
		}
	}
}