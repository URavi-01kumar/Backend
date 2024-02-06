package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.Entities.Post;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;

public interface PostService {
	
	//create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//get all posts
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	//get Single post
	
	PostDto getPostById(Integer postId);
	
	//get all post by categery 
	
	List<PostDto> getPostByCategory (Integer categoryId);
	
	//get all Posts by user
	
	List<PostDto> getPostByUser(Integer userId);
	
	//Deleting Post
	void deletePostById(Integer postId);
	
	//Serch post
	
	List<PostDto> searchPosts(String keyword);


	
	
	
	

}
