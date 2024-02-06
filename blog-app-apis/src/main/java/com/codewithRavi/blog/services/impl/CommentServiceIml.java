package com.codewithdurgesh.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.codewithdurgesh.blog.Entities.Comment;
import com.codewithdurgesh.blog.Entities.Post;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.CommentDto;
import com.codewithdurgesh.blog.repositories.CommentRepo;
import com.codewithdurgesh.blog.repositories.PostRepo;
import com.codewithdurgesh.blog.services.CommentService;

public class CommentServiceIml implements CommentService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper moddelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","Post Id",postId));
		 Comment comment= this.moddelMapper.map(commentDto, Comment.class);
		 
		 comment.setPost(post);
		 Comment savedComment=((CrudRepository<Comment, Integer>) this.commentRepo).save(comment);
		 return this.moddelMapper.map(savedComment, CommentDto.class);
		 
		
		
		
	
	}

	@Override
	public Void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		Comment com= this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","COmment Id",commentId));
		this.commentRepo.delete(com);
		return null;
		
	}


}
