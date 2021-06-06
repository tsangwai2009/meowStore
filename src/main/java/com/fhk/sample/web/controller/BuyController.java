package com.fhk.sample.web.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhk.sample.domain.entity.BookBean;
import com.fhk.sample.domain.entity.CashBean;
import com.fhk.sample.domain.entity.ChequeBean;
import com.fhk.sample.domain.entity.ContractBean;
import com.fhk.sample.domain.entity.ModeratorBean;
import com.fhk.sample.domain.entity.ShelfBean;
import com.fhk.sample.domain.entity.UserBean;
import com.fhk.sample.service.BookService;
import com.fhk.sample.service.BuyService;
import com.fhk.sample.service.ModeratorService;
import com.fhk.sample.service.UserService;


@RestController
@RequestMapping(value = "/rest/buy/")
public class BuyController //Control access
{
	@Inject
	private BuyService buyService;
	@Inject
	private UserService userService;
	@Inject
	private BookService bookService;	
	@Inject
	private ModeratorService moderatorService;
	
	@RequestMapping(value = "find-cheque-by-id", method = RequestMethod.POST) // only moderator can check all record
	public ChequeBean findBookById(@RequestParam(value = "chequeId", required = true) final Long chequeId)  
	{
		if(!adminLoginCheck()) {return null;}
		return buyService.findById(chequeId);
	}
	
	@RequestMapping(value = "checkbalance", method = RequestMethod.GET)
	public BigDecimal checkBalance()
	{
		if(!userLoginCheck()) {return null;}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username= authentication.getName().toString();
		UserBean user= userService.findByUsername(username);
		Long userId = user.getUserId();
		return buyService.checkBalance(userId);
	}
	
	@RequestMapping(value = "cashbuy", method = RequestMethod.POST)
	public Map<String,String> cashbuy(@RequestParam(value = "bookId", required = true) final Long bookId)
	{
		Map<String,String> returnData = new HashMap<String,String>();
		returnData.put("notOwn", "Yes");
		if(!userLoginCheck()) {
			returnData.put("msg", "You have not logined user system. Please login first");
			return returnData;
		}
		
		BookBean targetBook = bookService.findById(bookId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username= authentication.getName().toString();
		UserBean user= userService.findByUsername(username);
		Long userId = user.getUserId();
		
		//Check balance
		BigDecimal currentBalance = buyService.checkBalance(userId);
		if(targetBook.getPrice().compareTo(currentBalance)>0) {
			returnData.put("msg", "Error occured: Balance not enough. Please check again.");
			return returnData;
		}
		//Check exist
		ShelfBean existShelf = buyService.findShelf(userId,bookId);
		if(existShelf!=null) {			
			returnData.put("msg", "Error occured: You already brought.");
			return returnData;
			}

		buyService.addCashTransact(userId,targetBook.getPrice().negate(),"Buy for "+targetBook.getIsbn(),bookId);
		returnData.put("msg", "Success! You can read the book in your shelf.");
		returnData.put("notOwn", "");
		return returnData;
	}
	
	@RequestMapping(value = "chequebuy", method = RequestMethod.POST)
	public Map<String,String> chequebuy(@RequestParam(value = "bookId", required = true) final Long bookId,
			@RequestParam(value = "amount", required = true) final BigDecimal amount){
		Map<String,String> returnData = new HashMap<String,String>();
		if(!userLoginCheck()) {
			returnData.put("msg", "You have not logined user system. Please login first.");
			return returnData;
		}
		BookBean targetBook = bookService.findById(bookId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username= authentication.getName().toString();
		UserBean user= userService.findByUsername(username);
		Long userId = user.getUserId();
		
		//Check amount
		boolean priceLarger = buyService.compareBalanceAndPrice(targetBook.getPrice(),amount);
		if(priceLarger) {
			returnData.put("msg", "The amount of cheque is smaller than book price. Please check again.");
			return returnData;
		}
		//Check shelf exist
		ShelfBean existShelf = buyService.findShelf(userId,bookId);
		if(existShelf!=null) {
			returnData.put("msg", "You have brought this book before. Please check again.");
			return returnData;
		}
		//Check ordered (contract)
		ContractBean existContract = buyService.findContract(userId,bookId);
		if(existContract!=null) {
			if (!existContract.getStatus().equals("Cancelled")) {
				returnData.put("msg", "You have ordered for this book before. Please check again.");
				return returnData;
			}
		}
		buyService.addChequeTransact(userId,bookId,amount);
		returnData.put("msg", "The request is received. Please send e-cheque and the book will be added to your shelf very soon.");
		return returnData;
	}
	
	@RequestMapping(value = "cashrecord", method = RequestMethod.POST)
	public List<CashBean> findCashAll()
	{
		if(!userLoginCheck()) {return null;}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username= authentication.getName().toString();
		UserBean user= userService.findByUsername(username);
		Long userId = user.getUserId();
		if(buyService.findCashAll(userId).isEmpty()) {
			return null;
		}
		return buyService.findCashAll(userId);
	}
	
	@RequestMapping(value = "chequerecord", method = RequestMethod.POST)
	public List<ChequeBean> findChequeAll()
	{
		if(!userLoginCheck()) {return null;}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username= authentication.getName().toString();
		UserBean user= userService.findByUsername(username);
		Long userId = user.getUserId();
		if(buyService.findChequeAll(userId).isEmpty()) {
			return null;
		}
		return buyService.findChequeAll(userId);
	}
	
	@RequestMapping(value = "userchequerecord", method = RequestMethod.POST) //All user cheque record
	public List<ChequeBean> findUserChequeAll()
	{
		if(adminLoginCheck()) {
			if(buyService.findUserChequeAll().isEmpty()) {
			return null;
			}
			return buyService.findUserChequeAll();
		}
		return null;
	}
	
	@RequestMapping(value = "terminate", method = RequestMethod.POST) //Both user and moderator can terminate the contract
	public void terminate(@RequestParam(value = "chequeId", required = true) final Long chequeId)
	{
		if(!userLoginCheck()&&!adminLoginCheck()) {return;}
		ChequeBean cheque = buyService.findByChequeId(chequeId);
		if(cheque.getContract().getApprovedBy()!=null) {return;}
		if(cheque.getStatus()=="Cancelled") {return;}
		buyService.terminate(chequeId);
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST) //edit contract, approve the cheque
	public Map<String,String> edit(
			@RequestParam(value = "contractId", required = true) final Long contractId,
			@RequestParam(value = "chequeId", required = true) final Long chequeId,
			@RequestParam(value = "userId", required = true) final Long userId,
			@RequestParam(value = "bookId", required = true) final Long bookId,
			@RequestParam(value = "amount", required = true) final BigDecimal amount,
			@RequestParam(value = "chequeStatus", required = true) final String status){
		Map<String,String> returnData = new HashMap<String,String>();
		if(!adminLoginCheck()) {
			returnData.put("msg", "You have not logined moderator system. Please login first.");
			return returnData;
		}
		ShelfBean existShelf = buyService.findShelf(userId,bookId);
		if(existShelf!=null) {
			returnData.put("msg", "The user has own the book. Please check.");
			return returnData;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Long moderatorId = moderatorService.findByUser(authentication.getName().toString(),
				authentication.getCredentials().toString()).getModeratorId();
		buyService.edit(contractId,chequeId,moderatorId,bookId,amount,status);		
		returnData.put("msg", "Success! The contract is edited.");
		return returnData;
	}
	
	@RequestMapping(value = "approve", method = RequestMethod.POST) //approve contract after approve the cheque
	public void approve(@RequestParam(value = "chequeId", required = true) final Long chequeId)
	{
		if(!adminLoginCheck()) {return;}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ChequeBean cheque = buyService.findByChequeId(chequeId);
		Long moderatorId = moderatorService.findByUser(authentication.getName().toString(),
				authentication.getCredentials().toString()).getModeratorId();
		if(cheque.getStatus().equals("Approved")) {buyService.approve(moderatorId,chequeId);}
		return;
	}
	
	@RequestMapping(value = "summary", method = RequestMethod.GET)
	public Map<String,String> summary(){
		if(!adminLoginCheck()) {return null;}
		Map<String,String> returnData = new HashMap<String,String>();
		String lastQuarter="";
		if(isTodayBetween(Calendar.DECEMBER, Calendar.FEBRUARY)) {lastQuarter="4";}
		else if(isTodayBetween(Calendar.MARCH, Calendar.MAY)) {lastQuarter="1";}
		else if(isTodayBetween(Calendar.JUNE, Calendar.AUGUST)) {lastQuarter="2";}
		else if(isTodayBetween(Calendar.SEPTEMBER, Calendar.NOVEMBER)) {lastQuarter="3";}
		Long completedContract = buyService.countCompletedContract(lastQuarter);
		Long voidContract = buyService.countVoidContract(lastQuarter);
		BigDecimal cash = buyService.countCash(lastQuarter);
		returnData.put("quarter", "Latest Summary for Quarter "+lastQuarter);
		returnData.put("completedContract", completedContract.toString());
		returnData.put("voidContract", voidContract.toString());
		returnData.put("cash", cash.toString());
		return returnData;
	}
	
	public boolean adminLoginCheck(){ //quick check is the acc moderator account
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			return true;
		}
		return false;
	}
	
	public boolean userLoginCheck(){ //quick check is the acc user account
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			return true;
		}
		return false;
	}
	
	private boolean isTodayBetween(int from, int to) {
	    if (from < 0 || to < 0 || from > Calendar.DECEMBER || to > Calendar.DECEMBER) {
	        throw new IllegalArgumentException("Invalid month provided: from = " + from + " to = " + to);
	    }
	    Date now = new Date();
	    GregorianCalendar cal = new GregorianCalendar();
	    cal.setTime(now);
	    int thisMonth = cal.get(Calendar.MONTH);
	    if (from > to) {
	        to = to + Calendar.DECEMBER;
	        thisMonth = thisMonth + Calendar.DECEMBER;
	    }
	    if (thisMonth >= from && thisMonth <= to) {
	        return true;
	    }
	    return false;
	}
	
}
