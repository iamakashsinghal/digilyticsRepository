/**
 * 
 */
package com.digilytics.digilyticsProject.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author akash
 *
 */
@Entity
@Table(name = "userTbl")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String userId;
	@Column(unique = true)
	private String emailId;
	private String Name;
	@ManyToOne
	@JoinColumn(name = "roleId")
	private Role role;
	private String errorRoll;
	private String errorEmail;
	@Column(nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean errorFlag;
	private String error;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getErrorRoll() {
		return errorRoll;
	}

	public void setErrorRoll(String errorRoll) {
		this.errorRoll = errorRoll;
	}

	public String getErrorEmail() {
		return errorEmail;
	}

	public void setErrorEmail(String errorEmail) {
		this.errorEmail = errorEmail;
	}

	public boolean isErrorFlag() {
		return errorFlag;
	}

	public void setErrorFlag(boolean errorFlag) {
		this.errorFlag = errorFlag;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
