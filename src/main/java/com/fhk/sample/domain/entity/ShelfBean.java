package com.fhk.sample.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="shelf")
public class ShelfBean implements Serializable {

	private static final long serialVersionUID = -4126788655432292921L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="shelf_seq")
	@SequenceGenerator(name="shelf_seq", sequenceName="shelf_seq")
	@Column(name="SHELF_ID")
	private Long shelfId;
	
	@Column(name="BOOK_ID")
	private Long bookId;
	
	@Column(name="USER_ID")
	private Long userId;
	
	@Column(name="LAST_ACCESS_DATE")
	private Date lastAccessDate;

	@Column(name="NUMBER_OF_ACCESSES")
	private Long numberOfAccesses;

	//Relation/////
		@OneToOne
		@JoinColumn(name="book_id",referencedColumnName = "book_id")
		private BookBean book;

		public BookBean getBook() {
			return book;
		}
		public void setBook(BookBean book) {
			this.book = book;
		}
	/////////////
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((book == null) ? 0 : book.hashCode());
			result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
			result = prime * result + ((numberOfAccesses == null) ? 0 : numberOfAccesses.hashCode());
			result = prime * result + ((shelfId == null) ? 0 : shelfId.hashCode());
			result = prime * result + ((userId == null) ? 0 : userId.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ShelfBean other = (ShelfBean) obj;
			if (book == null) {
				if (other.book != null)
					return false;
			} else if (!book.equals(other.book))
				return false;
			if (bookId == null) {
				if (other.bookId != null)
					return false;
			} else if (!bookId.equals(other.bookId))
				return false;
			if (numberOfAccesses == null) {
				if (other.numberOfAccesses != null)
					return false;
			} else if (!numberOfAccesses.equals(other.numberOfAccesses))
				return false;
			if (shelfId == null) {
				if (other.shelfId != null)
					return false;
			} else if (!shelfId.equals(other.shelfId))
				return false;
			if (userId == null) {
				if (other.userId != null)
					return false;
			} else if (!userId.equals(other.userId))
				return false;
			return true;
		}
		public Long getShelfId() {
			return shelfId;
		}
		public void setShelfId(Long shelfId) {
			this.shelfId = shelfId;
		}
		public Long getBookId() {
			return bookId;
		}
		public void setBookId(Long bookId) {
			this.bookId = bookId;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public Date getLastAccessDate() {
			return lastAccessDate;
		}
		public void setLastAccessDate(Date lastAccessDate) {
			this.lastAccessDate = lastAccessDate;
		}
		public Long getNumberOfAccesses() {
			return numberOfAccesses;
		}
		public void setNumberOfAccesses(Long numberOfAccesses) {
			this.numberOfAccesses = numberOfAccesses;
		}
	
	
}
	

