package com.fhk.sample.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

import com.fhk.sample.domain.entity.AuthorBean;
import com.fhk.sample.domain.entity.BookBean;
import com.fhk.sample.domain.entity.CategoryBean;
import com.fhk.sample.domain.entity.PublisherBean;
import com.fhk.sample.domain.entity.ShelfBean;
import com.fhk.sample.domain.entity.UserBean;
import com.fhk.sample.service.BookService;
import com.fhk.sample.service.BuyService;
import com.fhk.sample.service.UserService;

@RestController
@RequestMapping(value = "/rest/book")
public class BookController //Control access
{
	@Inject
	private BookService bookService;
	@Inject
	private UserService userService;
	@Inject
	private BuyService buyService;
	
	@RequestMapping(value = "find-book-by-id", method = RequestMethod.POST)
	public BookBean findBookById(@RequestParam(value = "bookId", required = true) final Long bookId)  
	{
		BookBean book = bookService.findById(bookId);
		book.setContent(null);
		return book;
	}
	
	@RequestMapping(value = "find-book-by-id-with-login", method = RequestMethod.POST)
	public BookBean findBookByIdwithLogin(@RequestParam(value = "bookId", required = true) final Long bookId)  
	{
		if(!userLoginCheck()) {return null;}
		Long userId = getCurrentUserId();
		ShelfBean existShelf = buyService.findShelf(userId,bookId);
		if(existShelf!=null) {
			return bookService.findById(bookId);
		}
		return null;
	}
	
	@RequestMapping(value = "find-book-by-id-with-admin", method = RequestMethod.POST)
	public BookBean findBookByIdwithAdmin(@RequestParam(value = "bookId", required = true) final Long bookId)  
	{
		if(!adminLoginCheck()) {return null;}
		return bookService.findById(bookId);
	}
	
	@RequestMapping(value = "find-book-all", method = RequestMethod.POST)
	public List<BookBean> findBookAll()  
	{
		List<BookBean> bookList = new ArrayList<BookBean>();
		for (BookBean book: bookService.findBookAll()) {
			book.setContent(null);  //mute the content of book for bookList
			bookList.add(book);
		}
		return bookList;
	}
	
	@RequestMapping(value = "find-shelf-all", method = RequestMethod.POST)
	public List<ShelfBean> findShelfAll()
	{
		Long userId = getCurrentUserId();
		if(bookService.findShelfAll(userId).isEmpty()) {
			return null;
		}
		return bookService.findShelfAll(userId);

	}
	
	@RequestMapping(value = "find-author-all", method = RequestMethod.POST)
	public List<AuthorBean> findAuthorAll()
	{
		return bookService.findAuthorAll();
	}
	
	@RequestMapping(value = "find-publisher-all", method = RequestMethod.POST)
	public List<PublisherBean> findPublisherAll()
	{
		return bookService.findPublisherAll();
	}
	
	@RequestMapping(value = "find-category-all", method = RequestMethod.POST)
	public List<CategoryBean> findCategoryAll()
	{
		return bookService.findCategoryAll();
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Map<String,String> add(@RequestParam(value = "subject", required = true) final String subject,
			@RequestParam(value = "description", required = true) final String description,
			@RequestParam(value = "isbn", required = true) final String isbn,
			@RequestParam(value = "content", required = true) final String content,
			@RequestParam(value = "contentType", required = true) final String contentType,
			@RequestParam(value = "price", required = true) final BigDecimal price,
			@RequestParam(value = "author", required = true) final Long author,
			@RequestParam(value = "publisher", required = true) final Long publisher,
			@RequestParam(value = "category", required = true) final Long category){
		Map<String,String> returnData = new HashMap<String,String>();
		if(!adminLoginCheck()) {
			returnData.put("msg", "You have not logined moderator system. Please login first");
			return returnData;
		}
		BookBean existBook = bookService.findByISBN(isbn);
		if (existBook!=null) {
			returnData.put("msg", "The ISBN is existed (Each book should have its unique ISBN).");
			return returnData;
		}
		bookService.addBook(subject,description,isbn,content,contentType,price,author,publisher,category);
		returnData.put("msg", "Success! The book is added.");
		return returnData;
	}
	
	
	@RequestMapping(value = "addauthor", method = RequestMethod.POST)
	public Map<String,String> add(@RequestParam(value = "name", required = true) final String name,
			@RequestParam(value = "email", required = true) final String email,
			@RequestParam(value = "country", required = true) final String country){
		Map<String,String> returnData = new HashMap<String,String>();
		if(!adminLoginCheck()) {
			returnData.put("msg", "You have not logined moderator system. Please login first");
			return returnData;
		}
		AuthorBean existAuthor = bookService.findByEmail(email);
		if (existAuthor!=null) {
			returnData.put("msg", "The author is existed (Each author should have its unique email).");
			return returnData;
		}
		else {
			bookService.addAuthor(name,email,country);
			returnData.put("msg", "Success! New author is added.");
		}
		return returnData;
	}
	
	
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public Map<String,String> modify(
			@RequestParam(value = "bookId", required = true) final Long bookId,
			@RequestParam(value = "subject", required = true) final String subject,
			@RequestParam(value = "description", required = true) final String description,
			@RequestParam(value = "isbn", required = true) final String isbn,
			@RequestParam(value = "content", required = true) final String content,
			@RequestParam(value = "contentType", required = true) final String contentType,
			@RequestParam(value = "price", required = true) final BigDecimal price,
			@RequestParam(value = "author", required = true) final Long author,
			@RequestParam(value = "publisher", required = true) final Long publisher,
			@RequestParam(value = "category", required = true) final Long category){
		Map<String,String> returnData = new HashMap<String,String>();
		if(!adminLoginCheck()) {
			returnData.put("msg", "You have not logined moderator system. Please login first");
			return returnData;
		}
		BookBean existBook = bookService.findByISBN(isbn);
		if (existBook!=null) {
			BookBean thisBook = bookService.findById(bookId);
			if(thisBook.getIsbn()!=existBook.getIsbn()) {		
				returnData.put("msg", "The edited ISBN is existed in database (Each book should have its unique ISBN).");
				return returnData;
				}
		}
		bookService.modifyBook(bookId,subject,description,isbn,content,contentType,price,author,publisher,category);
		returnData.put("msg", "Success! The book is modified.");
		return returnData;
	}
	
	//Shelf Access update(last access time and access count)
	@RequestMapping(value = "update-access", method = RequestMethod.POST)
	public void updateShelfAccess(@RequestParam(value = "bookId", required = true) final Long bookId)
	{
		Long userId = getCurrentUserId();
		bookService.updateShelf(userId,bookId);
	}
	
	public boolean adminLoginCheck(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			return true;
		}
		return false;
	}
	
	public boolean userLoginCheck(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			return true;
		}
		return false;
	}
	
	private Long getCurrentUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username= authentication.getName().toString();
		UserBean user= userService.findByUsername(username);
		return user.getUserId();
	}
	
}