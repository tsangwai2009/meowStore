package com.fhk.sample.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fhk.sample.domain.entity.CategoryBean;

public interface CategoryDao extends JpaRepository<CategoryBean, Long> { //DAO provide ability of CRUD, BEAN convert DB to JAVA form
 
}
