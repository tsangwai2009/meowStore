package com.fhk.sample;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.fhk.sample.service.TestService;

public class TestSandbox 
{
	public static void main(final String[] args)
	{
		final ApplicationContext ctx = new SpringApplicationBuilder(SampleApplication.class)
				.web(false)
                .run(args); 
		
		final TestService ts = ctx.getBean(TestService.class);
		ts.findTestAll();
		
	}

}
