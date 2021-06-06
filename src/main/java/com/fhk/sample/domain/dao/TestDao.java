package com.fhk.sample.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fhk.sample.domain.entity.TestBean;

public interface TestDao extends JpaRepository<TestBean, Long>
{

}
