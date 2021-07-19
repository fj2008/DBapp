package com.korea.dbapp.domain.comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.korea.dbapp.domain.post.Post;
import com.korea.dbapp.domain.user.User;

import lombok.Data;

@Data
//Getter,Setter , ToString 을 만들어준다.

@Entity
public class Comment {
	@Id//기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// GenerationType내부 함수를 선택해서 기본키식별전략을 선택한다
	// index를 만든다고 무조건 좋은것은아니다.
	// 예를들어서 10년동안의 온도를 모아서 평균을 구한다면
	// 다 더해서 나누면 되기때문에 index를 만들면 데이터를 집어넣을때 더 복잡함만 추가될 뿐이다.
	//프라이머리 키(기본키)
	private int id;
	
	private String text;
	//한명의 유저는 여러개의 댓글을 작성할수 있다
	//하나의 댓글은 여려명의 유저가 작성할 수 없다
	//유저가 1 코멘트가 N
	@JoinColumn(name = "user_id")//폴인키를 user_id로 만들도록 한다.
	@ManyToOne
	private User user;
	//하나의 게시글은 여려개의 댓글을 가질수 있다
	//하나의 댓글은 여려개의 포스트를 만들어낼수 있다? 말이안된다
	//그렇기때문에 게시글이 1 댓글이 N
	
	@JoinColumn(name = "post_id")//폴인키를 post_id로 만들도록 한다.
	@ManyToOne
	private Post post;
	
	
}
