package com.fhk.sample.service;


import com.fhk.sample.domain.entity.ModeratorBean;

public interface ModeratorService //Related to moderator login
{
	ModeratorBean findByUser(final String username, final String password);
	//ModeratorBean findByUsername(final String username);
}
