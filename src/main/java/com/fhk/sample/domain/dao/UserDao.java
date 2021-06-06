package com.fhk.sample.domain.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.fhk.sample.domain.entity.UserBean;

public interface UserDao extends JpaRepository<UserBean, Long> { //DAO provide ability of CRUD, BEAN convert DB to JAVA form
 
	//Extra findUser(UserBean)
	public UserBean findByUsernameAndPassword(String username,String password);
	public UserBean findByUsername(String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE user_info u SET u.last_login_date = ?1 where u.username = ?2", nativeQuery = true)
	public void updateLastLoginDate(Date date, String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE user_info u SET u.approved_date = ?1 where u.username = ?2", nativeQuery = true)
	public void updateApprovedDate(Date date, String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE user_info u SET u.updated_date = ?1 where u.username = ?2", nativeQuery = true)
	public void updateUpdatedDate(Date date, String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE user_info u SET u.number_of_retries = ?1 where u.username = ?2", nativeQuery = true)
	public void updateNumberOfRetries(long retry, String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE user_info u SET u.status = ?1 where u.username = ?2", nativeQuery = true)
	public void updateStatus(String status, String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE user_info u SET u.password = ?1 where u.username = ?2", nativeQuery = true)
	public void updatePassword(String password, String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE user_info u SET u.approved_by = ?1 where u.username = ?2", nativeQuery = true)
	public void updateApprovedBy(Long moderatorId, String username);

	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO user_info u (u.username, u.password, u.name, u.email, u.telephone, u.mobile, u.address, u.status, u.created_date,u.number_of_retries, u.role) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, 'pending', ?8, 0, 'ROLE_USER')", nativeQuery = true)
	public void addUser(String username, String password, String name, String email, String telephone, String mobile,
			String address, Date date);
	
}
