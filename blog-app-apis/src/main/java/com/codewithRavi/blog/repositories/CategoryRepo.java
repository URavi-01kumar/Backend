package com.codewithdurgesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdurgesh.blog.Entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
