package com.fhk.sample.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhk.sample.domain.dao.AuthorDao;
import com.fhk.sample.domain.dao.BookDao;
import com.fhk.sample.domain.dao.CategoryDao;
import com.fhk.sample.domain.dao.PublisherDao;
import com.fhk.sample.domain.dao.ShelfDao;
import com.fhk.sample.domain.entity.AuthorBean;
import com.fhk.sample.domain.entity.BookBean;
import com.fhk.sample.domain.entity.CategoryBean;
import com.fhk.sample.domain.entity.PublisherBean;
import com.fhk.sample.domain.entity.ShelfBean;
import com.fhk.sample.service.BookService;

@Service
class BookServiceImpl implements BookService
{
	@Inject
	private BookDao bookDao;
	@Inject
	private AuthorDao authorDao;
	@Inject
	private PublisherDao publisherDao;	
	@Inject
	private CategoryDao categoryDao;
	@Inject
	private ShelfDao shelfDao;
	

	@Override
	@Transactional
	public void addBook(String subject, String description, String isbn, String content, 
			String contentType, BigDecimal price,
			Long author, Long publisher, Long category) {
		bookDao.addBook(subject,description,isbn,content,contentType,new Date(),price,author,publisher,category);
	}
	
	@Override
	@Transactional
	public void addAuthor(String name, String email, String country) {
		authorDao.addAuthor(name,email,country);
	}
	
	@Override
	@Transactional
	public void modifyBook(Long bookId, String subject, String description, String isbn, String content,
			String contentType, BigDecimal price, Long author, Long publisher, Long category) {
		bookDao.modifyBook(bookId,subject,description,isbn,content,contentType,new Date(),price,author,publisher,category);
	}
	
	@Override
	@Transactional
	public void updateShelf(Long userId,Long bookId) {
		// Continue to implement in here and DAO
		ShelfBean shelf = shelfDao.findByUserIdAndBookId(userId, bookId);
		long noOfAccesses = shelf.getNumberOfAccesses();
		shelfDao.updateNumberOfAccesses(noOfAccesses+1,userId,bookId);
		shelfDao.updateLastLoginDate(new Date(),userId,bookId);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<BookBean> findBookAll() {
		List<BookBean> books = bookDao.findAll();
		return books;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<AuthorBean> findAuthorAll() {
		List<AuthorBean> authors = authorDao.findAll();
		return authors;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<PublisherBean> findPublisherAll() {
		List<PublisherBean> publishers = publisherDao.findAll();
		return publishers;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<CategoryBean> findCategoryAll() {
		List<CategoryBean> categorys = categoryDao.findAll();
		return categorys;
	}


	@Override
	@Transactional(readOnly=true)
	public BookBean findByISBN(final String isbn) {
		return bookDao.findByIsbn(isbn);
	}

	@Override
	@Transactional(readOnly=true)
	public BookBean findById(Long bookId) {
		return bookDao.findByBookId(bookId);
	}

	@Override
	public List<ShelfBean> findShelfAll(Long userId) {
		return shelfDao.findByUserId(userId);
	}

	@Override
	public AuthorBean findByEmail(String email) {
		return authorDao.findByEmail(email);
	}

	
	
//	public List<String> findAuthorNameAll(List<BookBean> books){
//		List<String> authorNames = new ArrayList<String>();
//		for (BookBean book: books) {
//			authorNames.add(authorDao.findOne(book.getAuthorId()).getName());
//		}
//		return authorNames;
//	}
	
}
