package com.common.security;



public class User {

	
	private Integer userId;

	
	private String userName;

	private Integer scope;

	private Integer isActive;

	
	private Integer isGroupAdmin;

	private String userDomain;

	
	public User() {
	}


	public User(final Integer userId, final String userName, final Integer scope, final Integer active,
			Integer isGroupAdmin, String userDomain) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.scope = scope;
		this.isActive = active;
		this.isGroupAdmin = isGroupAdmin;
		this.setUserDomain(userDomain);
	}

	
	public Integer getUserId() {
		return userId;
	}

	
	public void setUserId(final Integer userId) {
		this.userId = userId;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public Integer getScope() {
		return scope;
	}


	public void setScope(final Integer scope) {
		this.scope = scope;
	}

	public Integer getIsActive() {
		return isActive;
	}

	
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	
	public Integer getIsGroupAdmin() {
		return isGroupAdmin;
	}

	public void setIsGroupAdmin(Integer isGroupAdmin) {
		this.isGroupAdmin = isGroupAdmin;
	}

	
	public String getUserDomain() {
		return userDomain;
	}

	
	public void setUserDomain(String userDomain) {
		this.userDomain = userDomain;
	}

}
