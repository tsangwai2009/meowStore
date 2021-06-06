package com.fhk.sample.web.controller;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhk.sample.domain.entity.TestBean;
import com.fhk.sample.domain.entity.UserBean;
import com.fhk.sample.service.UserService;
import com.fhk.sample.web.security.MyUserDetailsService;

@RestController
@RequestMapping(value = "/rest/user")

public class UserController { //Control access
	@Inject
	private UserService userService;
	@Autowired
	private MyUserDetailsService myUserDetailService;
	
	@RequestMapping(value = "find-user-all", method = RequestMethod.POST)
	public List<UserBean> findUserAll() 
	{
		if(!adminLoginCheck()) {return null;}
		return userService.findUserAll();
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public UserBean findByUser(@RequestParam(value = "username", required = true) final String username, @RequestParam(value = "password", required = true) final String password)
	{
		UserBean user = userService.findByUser(username, password);
		if (user!=null) {
			if(user.getStatus().equals("approved")) {
				UserDetails userDetails = myUserDetailService.loadUserByUsername(user.getUsername());
				Authentication auth=new UsernamePasswordAuthenticationToken(userDetails,user.getPassword(), userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth); 
			}
		}
		return user;
	}
	
	@RequestMapping(value="check",method=RequestMethod.GET)
	public Collection<? extends GrantedAuthority> checkLogin() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	}
	
	@RequestMapping(value = "approve", method = RequestMethod.POST)
	public Map<String,String> approve(@RequestParam(value = "username", required = true) final String username)
	{
		if(!adminLoginCheck()) {return null;}
		userService.approve(username);
		return new HashMap<String,String>();
	}
	
	@RequestMapping(value = "reset", method = RequestMethod.POST)
	public Map<String,String> reset(@RequestParam(value = "username", required = true) final String username){
		if(!adminLoginCheck()) {return null;}
		userService.reset(username);
		return new HashMap<String,String>();
	}
	
	@RequestMapping(value = "reg", method = RequestMethod.POST)
	public Map<String,String> reg(@RequestParam(value = "username", required = true) final String username,
			@RequestParam(value = "password", required = true) final String password,
			@RequestParam(value = "name", required = true) final String name,
			@RequestParam(value = "email", required = true) final String email,
			@RequestParam(value = "telephone", required = true) final String telephone,
			@RequestParam(value = "mobile", required = true) final String mobile,
			@RequestParam(value = "address", required = true) final String address){	
		UserBean existUser = userService.findByUsername(username);
		if (existUser!=null) {
			return null;
		}
		userService.addUser(username,password,name,email,telephone,mobile,address);
		return new HashMap<String,String>();
	}
	
	public boolean adminLoginCheck(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			return true;
		}
		return false;
	}
}

