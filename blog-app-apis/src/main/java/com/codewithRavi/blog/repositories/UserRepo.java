package com.codewithdurgesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdurgesh.blog.Entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}

