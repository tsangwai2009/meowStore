package com.fhk.sample.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.fhk.sample.domain.entity.AuthorBean;

public interface AuthorDao extends JpaRepository<AuthorBean, Long> {

	public AuthorBean findByEmail(String email); //DAO provide ability of CRUD, BEAN convert DB to JAVA form

	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO author a (a.name,a.email,a.country) VALUES (?1, ?2, ?3)", nativeQuery = true)
	public void addAuthor(String name, String email, String country); 
 
}
