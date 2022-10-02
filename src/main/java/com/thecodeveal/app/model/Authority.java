package com.thecodeveal.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Table(name="AUTH_AUTHORITY")
@Entity
public class Authority implements GrantedAuthority{
	
	
	public Authority(String roleName) {
		super();
		this.roleName = roleName;
	}



	public Authority() {
		// TODO Auto-generated constructor stub
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="ROLE_NAME")
	private String roleName;
	
	

	@Override
	public String getAuthority() {
		return roleName;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getRoleName() {
		return roleName;
	}



	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	

}
