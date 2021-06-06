package com.fhk.sample.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fhk.sample.domain.entity.PublisherBean;

public interface PublisherDao extends JpaRepository<PublisherBean, Long> { //DAO provide ability of CRUD, BEAN convert DB to JAVA form
 
}
