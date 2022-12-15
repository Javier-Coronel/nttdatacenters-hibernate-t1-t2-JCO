package com.nttdata.hibernate.persistence;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Column;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable{

	/** SERIAL VERSION */
	private static final long serialVersionUID = 1L;
	
	private String updatedUser;
	
	/**
	 * GET ID
	 * 
	 * @return Long
	 */
	@Transient
	public abstract Long getId();
	
	/**
	 * SET ID
	 * 
	 * @param id
	 */
	public abstract void setId(final Long id);
	
	/**
	 * @return the updatedUser
	 */
	@Column(name = "AUDIT_UPDATED_USER", nullable = false)
	public String getUpdatedUser() {
		return updatedUser;
	}
	
	/**
	 * @param updatedUser The updatedUser to set
	 */
	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}
}
