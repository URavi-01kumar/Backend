package com.codewithdurgesh.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codewithdurgesh.blog.Entities.Category;
import com.codewithdurgesh.blog.Entities.Post;
import com.codewithdurgesh.blog.Entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{ //short cut for see implemented metheod ctrl+shigt+t

	List<Post> findByCategory(Category category);

	List<Post> findByUser(User user);
	
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String title);

	

	

}

