package com.korea.dbapp.domain.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	@Query(value = "SELECT * FROM comment WHERE post_id =:id",nativeQuery = true)
	List<Comment> mfindAllByPostId(int id);
}
