package com.fhk.sample.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name="cheque")
public class ChequeBean implements Serializable {

	private static final long serialVersionUID = -4126788655432292921L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cheque_seq")
	@SequenceGenerator(name="cheque_seq", sequenceName="cheque_seq")
	@Column(name="CHEQUE_ID")
	private Long chequeId;
	
	@Column(name="USER_ID")
	private Long userId;
	
	@Column(name="CONTRACT_ID")
	private Long contractId;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="AMOUNT")
	private BigDecimal amount;
	
	@Column(name="STATUS")
	private String status;

	@Column(name="APPROVED_DATE")
	private Date approvedDate;
	
	@Column(name="APPROVED_BY")
	private Long approvedBy;
	
//Relation/////
		
		@OneToOne
		@JoinColumn(name="contract_id",referencedColumnName = "contract_id")
		private ContractBean contract;

		public ContractBean getContract() {
			return contract;
		}
		public void setContract(ContractBean contract) {
			this.contract = contract;
		}	
		
		
		@Formula("(select m.username from moderator m where m.moderator_id = approved_by)")
		private String moderatorname;
		public String getModeratorName() {
			return moderatorname;
		}
		public void setModeratorName(String username) {
			this.moderatorname = username;
		}
/////////////
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((amount == null) ? 0 : amount.hashCode());
			result = prime * result + ((approvedBy == null) ? 0 : approvedBy.hashCode());
			result = prime * result + ((approvedDate == null) ? 0 : approvedDate.hashCode());
			result = prime * result + ((chequeId == null) ? 0 : chequeId.hashCode());
			result = prime * result + ((contract == null) ? 0 : contract.hashCode());
			result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
			result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
			result = prime * result + ((moderatorname == null) ? 0 : moderatorname.hashCode());
			result = prime * result + ((status == null) ? 0 : status.hashCode());
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
			ChequeBean other = (ChequeBean) obj;
			if (amount == null) {
				if (other.amount != null)
					return false;
			} else if (!amount.equals(other.amount))
				return false;
			if (approvedBy == null) {
				if (other.approvedBy != null)
					return false;
			} else if (!approvedBy.equals(other.approvedBy))
				return false;
			if (approvedDate == null) {
				if (other.approvedDate != null)
					return false;
			} else if (!approvedDate.equals(other.approvedDate))
				return false;
			if (chequeId == null) {
				if (other.chequeId != null)
					return false;
			} else if (!chequeId.equals(other.chequeId))
				return false;
			if (contract == null) {
				if (other.contract != null)
					return false;
			} else if (!contract.equals(other.contract))
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
			if (userId == null) {
				if (other.userId != null)
					return false;
			} else if (!userId.equals(other.userId))
				return false;
			return true;
		}
		public Long getChequeId() {
			return chequeId;
		}
		public void setChequeId(Long chequeId) {
			this.chequeId = chequeId;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public Long getContractId() {
			return contractId;
		}
		public void setContractId(Long contractId) {
			this.contractId = contractId;
		}
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Date getApprovedDate() {
			return approvedDate;
		}
		public void setApprovedDate(Date approvedDate) {
			this.approvedDate = approvedDate;
		}
		public Long getApprovedBy() {
			return approvedBy;
		}
		public void setApprovedBy(Long approvedBy) {
			this.approvedBy = approvedBy;
		}
		public String getModeratorname() {
			return moderatorname;
		}
		public void setModeratorname(String moderatorname) {
			this.moderatorname = moderatorname;
		}
	
		
		
}
	

