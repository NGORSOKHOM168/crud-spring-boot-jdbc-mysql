package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.ResponseObject;
import com.learning.dto.ResultMessage;
import com.learning.dto.UserRequest;
import com.learning.dto.UserRequestById;
import com.learning.service.UserService;

@RestController 
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping({"","/list"})
	public ResponseObject<?> findAllUsers(){
		return userService.findAllUsers();
	}
	
	@PostMapping("/create")
	public ResultMessage addNewUser(@RequestBody UserRequest request) {		
		return userService.addNewUser(request);		
	}

	@PostMapping("update")
	public ResultMessage updateUser(@RequestBody UserRequest request) {		
		return userService.updateUser(request);		
	}

	@PostMapping("delete")
	public ResultMessage deleteUser(@RequestBody UserRequestById userRequestById) {		
		return userService.deleteUser(userRequestById);		
	}
	

}
