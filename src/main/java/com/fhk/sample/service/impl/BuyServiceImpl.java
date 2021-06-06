package com.fhk.sample.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhk.sample.domain.dao.BookDao;
import com.fhk.sample.domain.dao.CashDao;
import com.fhk.sample.domain.dao.ChequeDao;
import com.fhk.sample.domain.dao.ContractDao;
import com.fhk.sample.domain.dao.ShelfDao;
import com.fhk.sample.domain.entity.BookBean;
import com.fhk.sample.domain.entity.CashBean;
import com.fhk.sample.domain.entity.ChequeBean;
import com.fhk.sample.domain.entity.ContractBean;
import com.fhk.sample.domain.entity.ShelfBean;
import com.fhk.sample.service.BuyService;


@Service
class BuyServiceImpl implements BuyService
{
	@Inject
	private CashDao cashDao;
	@Inject
	private ShelfDao shelfDao;
	@Inject
	private ContractDao contractDao;
	@Inject
	private ChequeDao chequeDao;
	@Inject
	private BookDao bookDao;
	
	@Override
	@Transactional
	public BigDecimal checkBalance(Long userId) {
		return cashDao.checkBalance(userId);
	}

	@Override
	public boolean compareBalanceAndPrice(BigDecimal price, BigDecimal amount) {
		if(price.compareTo(amount)>0) {return true;}
		else return false;
	}
	
	@Override
	@Transactional
	public ShelfBean findShelf(Long userId, Long bookId) {
		return shelfDao.findByUserIdAndBookId(userId,bookId);
	}

	@Override
	@Transactional
	public void addCashTransact(Long userId, BigDecimal amount, String remarks,Long bookId) {
		// TODO Auto-generated method stub
		cashDao.addCashTransact(userId,amount,remarks,new Date());
		shelfDao.addShelf(userId,bookId,(long)0);
	}

	@Override
	@Transactional
	public List<CashBean> findCashAll(Long userId) {
		return cashDao.findByUserId(userId);
	}

	@Override
	@Transactional
	public ContractBean findContract(Long userId, Long bookId) {
		List<ContractBean> contractList = contractDao.findByUserIdAndBookId(userId,bookId);
		if(contractList.isEmpty()) {return null;}
		return contractList.get(contractList.size()-1);
	}

	@Override
	@Transactional
	public void addChequeTransact(Long userId, Long bookId, BigDecimal amount) {
		contractDao.addContract(userId,bookId,new Date(), "Pending");
		List<ContractBean> contractList = contractDao.findByUserIdAndBookId(userId,bookId);
		ContractBean contractBean = contractList.get(contractList.size()-1);
		chequeDao.addCheque(userId,contractBean.getContractId(),new Date(),amount,"Not received");
	}

	@Override
	@Transactional
	public ChequeBean findById(Long chequeId) {
		return chequeDao.findByChequeId(chequeId);
	}
	
	@Override
	@Transactional
	public List<ChequeBean> findChequeAll(Long userId) {
		return chequeDao.findByUserId(userId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<ChequeBean> findUserChequeAll() {
		return chequeDao.findAll();
	}
	
	@Override
	@Transactional
	public ChequeBean findByChequeId(Long chequeId) {
		return chequeDao.findByChequeId(chequeId);
	}

	@Override
	@Transactional
	public void terminate(Long chequeId) {
		chequeDao.updateStatus("Cancelled",chequeId);
		Long contractId = chequeDao.findByChequeId(chequeId).getContract().getContractId();
		Date date = new Date();
		contractDao.updateUpdatedDate(date,contractId);
		contractDao.updateVoidedDate(date,contractId);
		contractDao.updateStatus("Cancelled",contractId);
		chequeDao.voidApprovedBy(chequeId);
		chequeDao.voidApprovedDate(chequeId);
	}


	@Override
	@Transactional
	public void edit(Long contractId, Long chequeId, Long moderatorId, Long bookId, BigDecimal amount, String status) {
		chequeDao.updateAmount(amount,chequeId);
		String currentChequeStatus=chequeDao.findByChequeId(chequeId).getStatus();
		if((!currentChequeStatus.equals("Approved"))&&status.equals("Approved")) { //not approved to approved
			chequeDao.updateStatus(status,chequeId);
			chequeDao.updateApprovedBy(moderatorId,chequeId);
			chequeDao.updateApprovedDate(new Date(),chequeId);
		}
		else if(currentChequeStatus.equals("Approved")&&(!status.equals("Approved"))) { //approved to not approved
			chequeDao.updateStatus(status,chequeId);
			chequeDao.voidApprovedBy(chequeId);
			chequeDao.voidApprovedDate(chequeId);
		}
		else if(!currentChequeStatus.equals(status)) {chequeDao.updateStatus(status,chequeId);}
		contractDao.updateBookId(bookId,contractId);
		contractDao.updateUpdatedDate(new Date(), contractId);
	}

	@Override
	@Transactional
	public void approve(Long moderatorId, Long chequeId) {
		ChequeBean cheque = chequeDao.findByChequeId(chequeId);
		ContractBean contract = cheque.getContract();
		BookBean targetBook = bookDao.findByBookId(contract.getBookId());
		Long contractId = cheque.getContractId();
		contractDao.updateApprovedBy(moderatorId,contractId);
		contractDao.updateStatus("Approved",contractId);
		contractDao.updateUpdatedDate(new Date(), contractId);
		//Give book
		if(cheque.getAmount().equals(targetBook.getPrice())) {
			cashDao.addCashTransact(contract.getUserId(),new BigDecimal("0"),"Buy for "+targetBook.getIsbn(),new Date());
		}
		else {
			cashDao.addCashTransact(contract.getUserId(),cheque.getAmount().subtract(targetBook.getPrice()),"Buy for "+targetBook.getIsbn()+" and remainder",new Date());
		}
		shelfDao.addShelf(contract.getUserId(),targetBook.getBookId(),(long)0); //0 means initial access time
	}

	@Override
	@Transactional
	public Long countCompletedContract(String lastQuarter) {
		int thisYear = getCurrentYear();
	    Date startDate = startDateSet(thisYear,lastQuarter);
	    Date endDate= endDateSet(thisYear,lastQuarter);
		Long countNo = contractDao.countCompletedContract(startDate,endDate);
		return countNo;
	}
	
	@Override
	@Transactional
	public Long countVoidContract(String lastQuarter) {
	    int thisYear = getCurrentYear();
	    Date startDate = startDateSet(thisYear,lastQuarter);
	    Date endDate= endDateSet(thisYear,lastQuarter);
		Long countNo = contractDao.countVoidContract(startDate,endDate);
		return countNo;
	}
	
	@Override
	@Transactional
	public BigDecimal countCash(String lastQuarter) {
		int thisYear = getCurrentYear();
	    Date startDate = startDateSet(thisYear,lastQuarter);
	    Date endDate= endDateSet(thisYear,lastQuarter);
	    BigDecimal cash= cashDao.countCash(startDate,endDate);
	    if(cash==null) {return BigDecimal.ZERO;} //no record then 0
	    return cash;
	}
	
	
	public int getCurrentYear() {
		Date now = new Date();
	    GregorianCalendar cal = new GregorianCalendar();
	    cal.setTime(now);
	    return cal.get(Calendar.YEAR);
	}
	
	public Date startDateSet(int thisYear,String quarter) {
		if(quarter.equals("2")) {
			return new GregorianCalendar(thisYear, Calendar.MARCH, 1).getTime();
		}
		else if(quarter.equals("3")) {
			return new GregorianCalendar(thisYear, Calendar.JUNE, 1).getTime();
		}
		else if(quarter.equals("4")) {
			return new GregorianCalendar(thisYear, Calendar.SEPTEMBER, 1).getTime();		
				}
		else if(quarter.equals("1")) {
			return new GregorianCalendar(thisYear-1, Calendar.DECEMBER, 1).getTime();
		}
		return new Date();
	}
	
	public Date endDateSet(int thisYear,String quarter) {
		if(quarter.equals("2")) {
			return new GregorianCalendar(thisYear, Calendar.MAY, 31).getTime();
		}
		else if(quarter.equals("3")) {
			return new GregorianCalendar(thisYear, Calendar.AUGUST, 31).getTime();
		}
		else if(quarter.equals("4")) {
			return new GregorianCalendar(thisYear, Calendar.NOVEMBER, 30).getTime();	
				}
		else if(quarter.equals("1")) {
			if(thisYear%4==0) {return new GregorianCalendar(thisYear, Calendar.FEBRUARY, 29).getTime();}
			else {return new GregorianCalendar(thisYear, Calendar.FEBRUARY, 28).getTime();}
		}
		return new Date();
	}
	//@Override
	//@Transactional
	//public ModeratorBean findByUsername(final String username) { 
	//	return moderatorDao.findByUsername(username);
	//}



  
}
