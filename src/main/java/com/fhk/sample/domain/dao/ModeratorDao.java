package com.fhk.sample.domain.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.fhk.sample.domain.entity.ModeratorBean;

public interface ModeratorDao extends JpaRepository<ModeratorBean, Long> { //DAO provide ability of CRUD, BEAN convert DB to JAVA form
 
	/**
	  * 此方法相当于JPQL语句代码:select s from Student s where s.name = ?1 and s.address=?2
	  * @param name
	  * @param address
	  * @return ModeratorBean
	  */
	public ModeratorBean findByUsernameAndPassword(String username,String password);
	public ModeratorBean findByUsername(String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE moderator u SET u.last_login_date = ?1 where u.username = ?2", nativeQuery = true)
	public void updateLastLoginDate(Date date, String username);
	
	//@Modifying
	//@Query(value = "select * from moderator u where u.username = ?1", nativeQuery = true)
	//public ModeratorBean findByUsername(String username);
}
