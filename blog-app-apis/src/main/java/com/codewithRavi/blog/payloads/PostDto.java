package com.codewithdurgesh.blog.payloads;

import java.util.*;

import com.codewithdurgesh.blog.Entities.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor

public class PostDto {
	
	private Integer postId;
	
	private String title;
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	//jab post aaye to comment apne aap aa jayega
	private Set<Comment> comments= new HashSet<>();
	

}
