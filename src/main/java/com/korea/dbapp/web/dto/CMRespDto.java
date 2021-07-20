package com.korea.dbapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CMRespDto<T> {
	//dbtable랑 똑같이 생긴 오브젝트는 모델
	//요청통신을 위한 데이터RequestDto
	//응답통신을 위한 데이터ResponseDto 
	//통신할때는 무조건 Dto를 사용해야한다
	//ResponseDto 는 하나만 있으면된다 응답할때 제네릭타입으로 설정해서 유동적으로 타입을 바꿔주면됨
	//이때 받야아할타입이 하나 이상일때에는 인터페이스를 구현해서 상속받아야한다.
	private int code;
	private String msg;
	//하나의 타입을 유동적으로 상황에 따라서 받아야한다. 제네릭사용
	private T data;
}
