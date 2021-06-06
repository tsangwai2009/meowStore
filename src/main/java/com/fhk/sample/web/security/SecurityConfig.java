package com.fhk.sample.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@ConditionalOnWebApplication
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception 
	{
		http.authorizeRequests().anyRequest().permitAll()
		.and().logout().permitAll()
		.logoutSuccessHandler(logoutSuccessHandler())
		.and().csrf().disable();
	}

	@Override
	public void configure(final AuthenticationManagerBuilder auth) throws Exception 
	{
		auth //Builder Design Pattern
		.userDetailsService(myUserDetailsService);
	}
	
    @Bean
    LogoutSuccessHandler logoutSuccessHandler(){
        return  new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.sendRedirect("/main#/user/login"); //Both user and moderator logout go to user login page
            }
        };
    }
	
}

//http.authorizeRequests().anyRequest().permitAll()