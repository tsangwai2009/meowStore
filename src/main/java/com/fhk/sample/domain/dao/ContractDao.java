package com.fhk.sample.domain.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fhk.sample.domain.entity.ContractBean;

public interface ContractDao extends JpaRepository<ContractBean, Long>
{

	public List<ContractBean> findByUserIdAndBookId(Long userId, Long bookId);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO contract c (c.user_id,c.book_id, c.created_date, c.status) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
	public void addContract(Long userId, Long bookId, Date createdDate, String status);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE contract c SET c.updated_date = ?1 where c.contract_id = ?2", nativeQuery = true)
	public void updateUpdatedDate(Date date, Long contractId);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE contract c SET c.voided_date = ?1 where c.contract_id = ?2", nativeQuery = true)
	public void updateVoidedDate(Date date, Long contractId);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE contract c SET c.status = ?1 where c.contract_id = ?2", nativeQuery = true)
	public void updateStatus(String status, Long contractId);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE contract c SET c.book_id = ?1 where c.contract_id = ?2", nativeQuery = true)
	public void updateBookId(Long bookId, Long contractId);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE contract c SET c.approved_by = ?1 where c.contract_id = ?2", nativeQuery = true)
	public void updateApprovedBy(Long moderatorId, Long contractId);

	@Query(value="Select count(*) from contract c where c.status='Approved' and c.updated_date BETWEEN :startDate AND :endDate",nativeQuery=true)
	public Long countCompletedContract(@Param("startDate") Date startDate,@Param("endDate") Date endDate);

	@Query(value="Select count(*) from contract c where c.status='Cancelled' and c.voided_date BETWEEN :startDate AND :endDate",nativeQuery=true)
	public Long countVoidContract(@Param("startDate") Date startDate,@Param("endDate") Date endDate);

	
}
