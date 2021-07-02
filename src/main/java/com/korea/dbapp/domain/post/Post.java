package com.korea.dbapp.domain.post;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.korea.dbapp.domain.user.User;

@Entity
public class Post {
	
	@Id//기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)// GenerationType내부 함수를 선택해서 기본키식별전략을 선택한다
	//프라이머리 키(기본키)
	private int id;
	// varchar(255) 255는 글자수
	private String title; 
	@Lob
	//jpa에서 제공하는 어노테이션
	//DB에longText타입으로 DB에 저장하기 위해서 사용한다.
	private String content;
	
	@JoinColumn(name = "user_id")
	@ManyToOne
	private User user; 
	// ORM(오브젝트 릴레이션 매핑)사용
	//자바에서 db로 모델링할때는 오브젝트로 할수 없다 데이터베이스의 무결성을 해치기때문
	//@ManyToOne 를 붙여주면 user태이블의 프라이머리키를 여기에 걸어서 폴인키로 만들어준다
	//오브젝트를 db로 모델링할때 생기는 모순을 jpa에서 간단하게 해결할 수 있도록 orm기능을 제공해주는것이다.
	
	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	
}
