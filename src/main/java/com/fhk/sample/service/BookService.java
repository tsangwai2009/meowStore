package com.fhk.sample.service;

import java.math.BigDecimal;
import java.util.List;

import com.fhk.sample.domain.entity.AuthorBean;
import com.fhk.sample.domain.entity.BookBean;
import com.fhk.sample.domain.entity.CategoryBean;
import com.fhk.sample.domain.entity.PublisherBean;
import com.fhk.sample.domain.entity.ShelfBean;

public interface BookService  //Related to book control
{
	List<BookBean> findBookAll();
	BookBean findById(final Long bookId);
	BookBean findByISBN(final String isbn);
	List<AuthorBean> findAuthorAll();
	List<PublisherBean> findPublisherAll();
	List<CategoryBean> findCategoryAll();
	
	void addBook(final String subject,final String description,final String isbn,
			final String content,final String contentType, final BigDecimal price,
			final Long author,final Long publisher,final Long category);
	
	void modifyBook(final Long bookId,final String subject,final String description,final String isbn,
			final String content,final String contentType,final BigDecimal price,
			final Long author,final Long publisher,final Long category);
	
	List<ShelfBean> findShelfAll(final Long userId);
	
	void updateShelf(Long userId, Long bookId);
	AuthorBean findByEmail(String email);
	void addAuthor(String name, String email, String country);

}
