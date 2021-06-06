package com.fhk.sample.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="moderator")
public class ModeratorBean implements Serializable {

	private static final long serialVersionUID = -4126788655432292921L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="moderator_seq")
	@SequenceGenerator(name="moderator_seq", sequenceName="moderator_seq")
	@Column(name="MODERATOR_ID")
	private Long moderatorId;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;

	@Column(name="LAST_LOGIN_DATE")
	private Date lastLoginDate;
	
	public Long getModeratorId() {
		return moderatorId;
	}

	public void setModeratorId(Long moderatorId) {
		this.moderatorId = moderatorId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lastLoginDate == null) ? 0 : lastLoginDate.hashCode());
		result = prime * result + ((moderatorId == null) ? 0 : moderatorId.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		ModeratorBean other = (ModeratorBean) obj;
		if (lastLoginDate == null) {
			if (other.lastLoginDate != null)
				return false;
		} else if (!lastLoginDate.equals(other.lastLoginDate))
			return false;
		if (moderatorId == null) {
			if (other.moderatorId != null)
				return false;
		} else if (!moderatorId.equals(other.moderatorId))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	

}
	

