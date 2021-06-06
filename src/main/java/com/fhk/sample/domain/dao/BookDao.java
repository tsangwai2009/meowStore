package com.fhk.sample.domain.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fhk.sample.domain.entity.BookBean;

public interface BookDao extends JpaRepository<BookBean, Long> { //DAO provide ability of CRUD, BEAN convert DB to JAVA form
	public BookBean findByIsbn(String isbn);
	public BookBean findByBookId(Long bookId);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO book b (b.subject,b.description,b.isbn,b.content,b.content_type,b.create_date,b.price,b.author_id,b.publisher_id,b.category_id) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10)", nativeQuery = true)
	public void addBook(String subject, String description, String isbn, String content, String contentType, Date date,
			BigDecimal price, Long author, Long publisher, Long category); //add new book
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE book b SET b.subject =:subject, b.description=:description, b.isbn=:isbn ,b.content=:content, b.content_type=:contentType ,b.create_date=:date ,b.price=:price ,b.author_id=:author ,b.publisher_id=:publisher, b.category_id=:category where b.book_id=:bookId", nativeQuery = true)
	public void modifyBook(@Param("bookId") Long bookId, @Param("subject") String subject,@Param("description")  String description,@Param("isbn")  String isbn,@Param("content")  String content,
			@Param("contentType") String contentType,@Param("date")  Date date,
			@Param("price") BigDecimal price,@Param("author")  Long author,@Param("publisher")  Long publisher,@Param("category")  Long category); //modify book info
}
