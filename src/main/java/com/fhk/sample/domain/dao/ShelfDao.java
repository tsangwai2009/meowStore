package com.fhk.sample.domain.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.fhk.sample.domain.entity.ShelfBean;

public interface ShelfDao extends JpaRepository<ShelfBean, Long> { //DAO provide ability of CRUD, BEAN convert DB to JAVA form
	public List<ShelfBean> findByUserId(Long userId);

	public ShelfBean findByUserIdAndBookId(Long userId, Long bookId);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO shelf s (s.user_id,s.book_id,s.number_of_accesses) VALUES (?1, ?2, ?3)", nativeQuery = true)
	public void addShelf(Long userId, Long bookId, Long noOfAccess);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE shelf s SET s.last_access_date = ?1 where s.user_id = ?2 AND s.book_id = ?3", nativeQuery = true)
	public void updateLastLoginDate(Date date, Long userId, Long bookId);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE shelf s SET s.number_of_accesses = ?1 where s.user_id = ?2 AND s.book_id = ?3", nativeQuery = true)
	public void updateNumberOfAccesses(Long access, Long userId, Long bookId);
}
