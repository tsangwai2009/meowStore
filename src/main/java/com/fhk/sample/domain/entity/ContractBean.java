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

import org.hibernate.annotations.Formula;

@Entity
@Table(name="contract")
public class ContractBean implements Serializable {

	private static final long serialVersionUID = -4126788655432292921L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="contract_seq")
	@SequenceGenerator(name="contract_seq", sequenceName="contract_seq")
	@Column(name="CONTRACT_ID")
	private Long contractId;
	
	@Column(name="USER_ID")
	private Long userId;
	
	@Column(name="BOOK_ID")
	private Long bookId;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name="VOIDED_DATE")
	private Date voidedDate;
	
	@Column(name="STATUS")
	private String status;

	@Column(name="APPROVED_BY",nullable = true)
	private Long approvedBy;
	
	@Column(name="TRANSACTION_ID")
	private Long transactionId;
	

//Relation/////
		@Formula("(select m.username from moderator m where m.moderator_id = approved_by)")
		private String moderatorname;
		public String getModeratorName() {
			return moderatorname;
		}
		public void setModeratorName(String username) {
			this.moderatorname = username;
		}
		
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
		public Long getContractId() {
			return contractId;
		}
		public void setContractId(Long contractId) {
			this.contractId = contractId;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public Long getBookId() {
			return bookId;
		}
		public void setBookId(Long bookId) {
			this.bookId = bookId;
		}
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}
		public Date getUpdatedDate() {
			return updatedDate;
		}
		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}
		public Date getVoidedDate() {
			return voidedDate;
		}
		public void setVoidedDate(Date voidedDate) {
			this.voidedDate = voidedDate;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Long getApprovedBy() {
			return approvedBy;
		}
		public void setApprovedBy(Long approvedBy) {
			this.approvedBy = approvedBy;
		}
		public Long getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(Long transactionId) {
			this.transactionId = transactionId;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((approvedBy == null) ? 0 : approvedBy.hashCode());
			result = prime * result + ((book == null) ? 0 : book.hashCode());
			result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
			result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
			result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
			result = prime * result + ((moderatorname == null) ? 0 : moderatorname.hashCode());
			result = prime * result + ((status == null) ? 0 : status.hashCode());
			result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
			result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
			result = prime * result + ((userId == null) ? 0 : userId.hashCode());
			result = prime * result + ((voidedDate == null) ? 0 : voidedDate.hashCode());
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
			ContractBean other = (ContractBean) obj;
			if (approvedBy == null) {
				if (other.approvedBy != null)
					return false;
			} else if (!approvedBy.equals(other.approvedBy))
				return false;
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
			if (contractId == null) {
				if (other.contractId != null)
					return false;
			} else if (!contractId.equals(other.contractId))
				return false;
			if (createdDate == null) {
				if (other.createdDate != null)
					return false;
			} else if (!createdDate.equals(other.createdDate))
				return false;
			if (moderatorname == null) {
				if (other.moderatorname != null)
					return false;
			} else if (!moderatorname.equals(other.moderatorname))
				return false;
			if (status == null) {
				if (other.status != null)
					return false;
			} else if (!status.equals(other.status))
				return false;
			if (transactionId == null) {
				if (other.transactionId != null)
					return false;
			} else if (!transactionId.equals(other.transactionId))
				return false;
			if (updatedDate == null) {
				if (other.updatedDate != null)
					return false;
			} else if (!updatedDate.equals(other.updatedDate))
				return false;
			if (userId == null) {
				if (other.userId != null)
					return false;
			} else if (!userId.equals(other.userId))
				return false;
			if (voidedDate == null) {
				if (other.voidedDate != null)
					return false;
			} else if (!voidedDate.equals(other.voidedDate))
				return false;
			return true;
		}

	
}
	

