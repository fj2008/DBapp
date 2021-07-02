package com.korea.dbapp.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
		
@Entity
public class User {
	@Id//기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)// GenerationType내부 함수를 선택해서 기본키식별전략을 선택한다
	private int id;//프라이머리 키(기본키)
	
	@Column(unique = true, length = 20)
	private String username;
	private String password;
	private String email;
	private String address;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
