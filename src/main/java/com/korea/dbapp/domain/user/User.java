package com.korea.dbapp.domain.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.springframework.context.annotation.Lazy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.korea.dbapp.domain.post.Post;
		
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
	
	//@OneToMany을 사용하면 post를 셀랙트할때 user를 join해서 select를 자동으로 해준다
	//유저한명이 여러개의 게시글을 쓸수 있다.
	//그렇기때문에 폴인키를 만들어주지 않는다
	//여기서 폴인키가 만들어지면 db원자성의 원칙이 깨질확률이 높기때문
	
	@JsonIgnoreProperties({"user"} )
	//user만 json으로 파싱하지 말라는 어노테이션
	@OneToMany(mappedBy = "user" , fetch = FetchType.EAGER)
	
	//mappedBy 는 나는 폴인키의 주인이 아니고 user가 폴인키이다 라고 알려주는것
	private List<Post>posts;
	//이건 db스키마에 영향을 미치는것이 아니다.
	//select할때만 영향을 주는것
	//양방향 매핑시의 주의할점 
	//1 mappedBy
	//2.@JsonIgnoreProperties 설정을 해줄것
	//왜냐하면 post와 user가 서로 양방향으로 join하면서 무한반복되는 상태가 되기때문이다.
	
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
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
