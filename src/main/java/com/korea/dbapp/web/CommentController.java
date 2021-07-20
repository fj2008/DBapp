package com.korea.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.dbapp.domain.comment.Comment;
import com.korea.dbapp.domain.comment.CommentRepository;
import com.korea.dbapp.domain.post.Post;
import com.korea.dbapp.domain.post.PostRepository;
import com.korea.dbapp.domain.user.User;
import com.korea.dbapp.web.dto.CMRespDto;
import com.korea.dbapp.web.dto.CommentSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CommentController {

	private final CommentRepository commentRepository;
	private final HttpSession session;
	private final PostRepository postRepository;

	// 1. svae -Post - Data Return 댓글 작성
	// data를 받을때 무조건 dto로 받아야한다
	// 벨리데이션체크용이성 등등의 이유때문
	@PostMapping("/comment")
	public @ResponseBody CMRespDto<Comment> save(@RequestBody CommentSaveReqDto dto) {
		User principal = (User) session.getAttribute("principal");
		

		// postid를 받아야한다.
		Comment comment = new Comment();
		comment.setText(dto.getText());
		// 댓글 set

		Post postEntity = postRepository.findById(dto.getPostId()).get();
		postEntity.setId(dto.getPostId());
		comment.setPost(postEntity);
		// postID를 comment에set

		comment.setUser(principal);

		Comment commentEntity = commentRepository.save(comment);
		return new CMRespDto<>(1, "댓글쓰기성공", commentEntity);

	}

	// 2. delete - Delete - Data Return 해당 댓글을 작성한 사람만 댓글 삭제

	@DeleteMapping("/comment/{id}")
	public @ResponseBody String deleteById(@PathVariable int id) {
		User principal = (User) session.getAttribute("principal");
		int userId = principal.getId();
		Comment commentEntity = commentRepository.findById(id).get();
		int commentUserId = commentEntity.getUser().getId();
		if (userId == commentUserId) {
			commentRepository.deleteById(id);
			return "ok";
		} else {
			return "fail";
		}
	}

}
