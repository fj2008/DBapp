package com.korea.dbapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//이 인터페이스의 책임은 데이터를 액세스해와서 자바 오브젝트로 바꿔주는 책임을 지게 할것이다.

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
//규칙
/*****
 * JpaRepository를 상속받으면 결국 이 JpaRepository가 DAO역할을 하면서 액세스를 편리하게 해준다.
 * DAO(데이터 액세스 오브젝트)가 만들어져있는것 여기 함수는 공통적으로 만들어 질 수 밖에 없는 DAO기능들을 다 제공한다. 
 * JpaRepository에 어노테이션이 있기때문에 상속만 받아도 메모리에 뜬다. 하지만 상속받지 않은경우 @Repository를 해줘야한다
 * */
	
	@Query(value = "SELECT * FROM user WHERE username=:username",nativeQuery = true)
	User mFindByUsername(String username);
	
	@Query(value = "SELECT * FROM user WHERE username=:username AND password=:password",nativeQuery = true)
	User mLogin(String username, String password);
}
