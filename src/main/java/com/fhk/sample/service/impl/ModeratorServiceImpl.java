package com.fhk.sample.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhk.sample.domain.dao.ModeratorDao;
import com.fhk.sample.domain.entity.ModeratorBean;
import com.fhk.sample.service.ModeratorService;

@Service
class ModeratorServiceImpl implements ModeratorService
{
	@Inject
	private ModeratorDao moderatorDao;

	@Override
	@Transactional
	public ModeratorBean findByUser(final String username, final String password) {
		ModeratorBean moderator = moderatorDao.findByUsernameAndPassword(username, password);
		if (moderator!=null) {
			Date date = new Date();
			moderatorDao.updateLastLoginDate(date,username);
		}
		return moderator;
	}

	//@Override
	//@Transactional
	//public ModeratorBean findByUsername(final String username) { 
	//	return moderatorDao.findByUsername(username);
	//}
  
}
