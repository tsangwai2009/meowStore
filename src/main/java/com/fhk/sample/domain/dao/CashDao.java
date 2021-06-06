package com.fhk.sample.domain.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fhk.sample.domain.entity.CashBean;

public interface CashDao extends JpaRepository<CashBean, Long> { //DAO provide ability of CRUD, BEAN convert DB to JAVA form
	
	@Query(value = "SELECT SUM(c.amount) FROM cash_acc_transaction c where c.user_id=?1", nativeQuery = true)
	public BigDecimal checkBalance(Long userId);

	
	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO cash_acc_transaction c (c.user_id,c.amount,c.remarks,c.created_date) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
	public void addCashTransact(Long userId, BigDecimal amount, String remarks, Date createdDate); //add cash record


	public List<CashBean> findByUserId(Long userId); 

	@Query(value="Select SUM(c.amount) from cash_acc_transaction c where c.created_date BETWEEN :startDate AND :endDate",nativeQuery=true)
	public BigDecimal countCash(@Param("startDate") Date startDate,@Param("endDate") Date endDate); //all cash account remainder
	
}
