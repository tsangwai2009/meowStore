package com.fhk.sample.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhk.sample.domain.dao.ModeratorDao;
import com.fhk.sample.domain.dao.UserDao;
import com.fhk.sample.domain.entity.UserBean;
import com.fhk.sample.service.UserService;

@Service
class UserServiceImpl implements UserService { 

	@Inject
	private UserDao userDao;
	@Inject
	private ModeratorDao moderatorDao;
	
	@Override
	@Transactional
	public void addUser(String username, String password, String name, String email, String telephone, String mobile,
			String address) {
		userDao.addUser(username,password,name,email,telephone,mobile,address,new Date());
	}
	
	@Override
	@Transactional
	public void updateUser(final UserBean user) { //Update
		userDao.save(user);
	}

	@Override
	@Transactional
	public void deleteUserByUserId(final Long userId) { //Delete
		userDao.delete(userId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<UserBean> findUserAll() { //Read
		return userDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public UserBean findByUsername(final String username) { //Read
		return userDao.findByUsername(username);
	}

	@Override
	@Transactional
	public UserBean findByUser(final String username, final String password) {
		UserBean user = userDao.findByUsernameAndPassword(username, password);
		if (user!=null) {
			if(user.getStatus().equals("approved")) {
				Date date = new Date();
				userDao.updateLastLoginDate(date,username);
			}
			if(user.getStatus().equals("blocked")) {return user;}
			userDao.updateNumberOfRetries(0, username);
		}
		else {
			user = userDao.findByUsername(username);
			if(user != null) {
				long noOfRetry = user.getNumberOfRetries();
				userDao.updateNumberOfRetries(noOfRetry+1,username);
				if (user.getNumberOfRetries()>4) {
					userDao.updateStatus("blocked",username);
				}
			}
			return null;
		}
		return user;
	}

	@Override
	@Transactional
	public void approve(String username) {
		Long moderatorID = moderatorDao.findByUsername(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString()).getModeratorId();
		userDao.updateStatus("approved",username);
		userDao.updateApprovedBy(moderatorID, username);
		Date date = new Date();
		userDao.updateApprovedDate(date,username);
		userDao.updateUpdatedDate(date,username);
	}
	
	@Override
	@Transactional
	public void reset(String username) {
		UserBean user = userDao.findByUsername(username);
		if (user.getApprovedBy()==null) {
			userDao.updateStatus("pending",username);
		}
		else {
			userDao.updateStatus("approved",username);
		}
		userDao.updateNumberOfRetries(0,username);
		userDao.updatePassword("123456",username);
	}

	
}
