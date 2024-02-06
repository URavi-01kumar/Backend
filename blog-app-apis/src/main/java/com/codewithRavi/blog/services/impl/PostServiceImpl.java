package com.codewithdurgesh.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.Entities.Category;
import com.codewithdurgesh.blog.Entities.Post;
import com.codewithdurgesh.blog.Entities.User;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;
import com.codewithdurgesh.blog.repositories.CategoryRepo;
import com.codewithdurgesh.blog.repositories.PostRepo;
import com.codewithdurgesh.blog.repositories.UserRepo;
import com.codewithdurgesh.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	
	

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id", userId));
		Category category= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category ID", categoryId));
		// TODO Auto-generated method stub
		Post post= this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.img");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost= this.postRepo.save(post);
		
		
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());;
		post.setImageName(postDto.getImageName());
		
		Post updatedPost= this.postRepo.save(post);
		
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	//this method get all post without pageable method  in down add pageable method
//	@Override
//	public List<PostDto> getAllPost() {
//		// TODO Auto-generated method stub
//		List<Post>allPosts= this.postRepo.findAll();
//		
//		List<PostDto> postDtos= allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
//		return postDtos;
//	}
//
	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
		
		
		
		return this.modelMapper.map(post, PostDto.class);
	}
	
	public PostResponse getAllPost(Integer PageNumber, Integer pageSize, String sortBy,String sortDir){
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();// here using ternery operator
		   //Sort sort=null;
//				if(sortDir.equalsIgnoreCase("asc")) {
//					sort=Sort.by(sortBy).ascending();
//					
//				}
//				else {
//					sort=Sort.by(sortBy).descending();
//				}
		
		Pageable pageable= PageRequest.of(PageNumber, pageSize,sort); //from domen
		Page <Post>pagePost= this.postRepo.findAll(pageable);
		
		List<Post> allPost= pagePost.getContent();	
		
		List<PostDto> postDtos= allPost.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse= new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageSize(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPages(pagePost.isLast());
	
		return postResponse;//yaha postResponse ka hi onject return karna hai
		
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId", categoryId));
		List<Post> posts= this.postRepo.findByCategory(cat);
		List<PostDto> postDtos= posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
       		
		return postDtos ;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserId", userId));
		List<Post>posts= this.postRepo.findByUser(user);
	 List<PostDto>postDtos=	posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
	
		
		
		return postDtos;
	}
	
	public void deletePostById(Integer postId) {
		
	Post posts=	this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
	this.postRepo.delete(posts);
	}


	 @Override
	    public List<PostDto> searchPosts(String keyword) {
	        List<Post> posts = this.postRepo.searchByTitle("%" + keyword + "%");
	        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	        return postDtos;
	    }

	

	
}
