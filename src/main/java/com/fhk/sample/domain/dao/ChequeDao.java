package com.fhk.sample.domain.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.fhk.sample.domain.entity.ChequeBean;

public interface ChequeDao extends JpaRepository<ChequeBean, Long>
{
	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO cheque c (c.user_id, c.contract_id, c.created_date, c.amount, c.status) VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
	public void addCheque(Long userId, Long contractId, Date date, BigDecimal amount, String status);

	public List<ChequeBean> findByUserId(Long userId);

	public ChequeBean findByChequeId(Long chequeId);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE cheque c SET c.status = ?1 where c.cheque_id = ?2", nativeQuery = true)
	public void updateStatus(String status, Long chequeId);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE cheque c SET c.amount = ?1 where c.cheque_id = ?2", nativeQuery = true)
	public void updateAmount(BigDecimal amount, Long chequeId);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE cheque c SET c.approved_by = ?1 where c.cheque_id = ?2", nativeQuery = true)
	public void updateApprovedBy(Long moderatorId, Long chequeId);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE cheque c SET c.approved_date = ?1 where c.cheque_id = ?2", nativeQuery = true)
	public void updateApprovedDate(Date date, Long chequeId);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE cheque c SET c.approved_by = NULL where c.cheque_id = ?1", nativeQuery = true)
	public void voidApprovedBy(Long chequeId);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE cheque c SET c.approved_date = NULL where c.cheque_id = ?1", nativeQuery = true)
	public void voidApprovedDate(Long chequeId);

}
