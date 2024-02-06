package com.codewithdurgesh.blog.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.services.UserService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	//create post
	//post post create user
	@PostMapping(name="/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto ){
		UserDto createUserDto=this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
	}
	
	//Put
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid ){
		UserDto updateUser=this.userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updateUser);
	}
	//delete post
	@DeleteMapping("/{userId}")
	public ResponseEntity <ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
		this.userService.deleteUser(uid);
		//return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("User delected successfull", true),HttpStatus.OK);
	}
	
	
	//Get user get
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok (this.userService.getAllUser());
	}
	//create method for single user getting
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
//		UserDto getSingleUser=this.userService.getUserById(userid);
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

}
