package com.korea.dbapp.web.dto;

import lombok.Data;

@Data
public class CommentSaveReqDto {
	private String text;
	//post_id를 하지 않는이유는 massegConvertor가 자바오브젝트로 파싱하기때문에 카멜표기법을 사용한다.
	//massegConvertor 내부에서 추상클래스일 확률이 높다. 왜냐하면 타입을 일치시키기때문
	
	private int postId;
	
}
