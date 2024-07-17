package com.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.dao.UserDao;
import com.learning.dto.ResponseObject;
import com.learning.dto.ResultMessage;
import com.learning.dto.UserRequest;
import com.learning.dto.UserRequestById;

@Service
public class UserService {
	
	@Autowired 
	private UserDao userDao;
	
	public ResultMessage addNewUser(UserRequest request) {		
		return userDao.addNewUser(request);		
	}
	
	public ResultMessage updateUser(UserRequest request) {		
		return userDao.updateUser(request);		
	}
	
	public ResultMessage deleteUser(UserRequestById userRequestById) {		
		return userDao.deleteUser(userRequestById);		
	}
	
	public ResponseObject<?> findAllUsers(){		
		return userDao.getUserList();	
	}


}
