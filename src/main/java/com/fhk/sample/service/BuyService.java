package com.fhk.sample.service;


import java.math.BigDecimal;
import java.util.List;

import com.fhk.sample.domain.entity.BookBean;
import com.fhk.sample.domain.entity.CashBean;
import com.fhk.sample.domain.entity.ChequeBean;
import com.fhk.sample.domain.entity.ContractBean;
import com.fhk.sample.domain.entity.ModeratorBean;
import com.fhk.sample.domain.entity.ShelfBean;

public interface BuyService //Related to buy function
{
	BigDecimal checkBalance(Long userId);
	ShelfBean findShelf(Long userId, Long bookId);
	
	void addCashTransact(Long userId, BigDecimal amount, String remarks,Long bookId);
	void addChequeTransact(Long userId, Long bookId, BigDecimal amount);
	
	List<CashBean> findCashAll(Long userId);
	ContractBean findContract(Long userId, Long bookId);
	List<ChequeBean> findChequeAll(Long userId);
	
	ChequeBean findByChequeId(Long chequeId);
	

	List<ChequeBean> findUserChequeAll();
	ChequeBean findById(Long chequeId);
	
	void terminate(Long chequeId);
	void edit(Long contractId, Long chequeId, Long moderatorId, Long bookId, BigDecimal amount, String status);
	void approve(Long moderatorId, Long chequeId);
	
	Long countCompletedContract(String lastQuarter);
	Long countVoidContract(String lastQuarter);
	BigDecimal countCash(String lastQuarter);
	boolean compareBalanceAndPrice(BigDecimal price, BigDecimal amount);
}
