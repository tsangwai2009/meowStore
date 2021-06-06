package com.fhk.sample.service;

import java.util.List;

import com.fhk.sample.domain.entity.UserBean;

public interface UserService { //related to user login and include moderator control user info
	
	void updateUser(final UserBean user);
	void deleteUserByUserId(final Long userId);
	
	List<UserBean> findUserAll();
	
	UserBean findByUser(final String username, final String password);
	
	void approve(final String username);
	void reset(final String username);
	UserBean findByUsername(final String username);
	
	void addUser(final String username,final String password,final String name,
			final String email,final String telephone, final String mobile,final String address);
}
