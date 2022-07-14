package com.demo.test.service;

import java.util.List;

import com.demo.test.entity.User;

public interface UserService {
	
	void save(User user);
	
	List<User> getUsers();
	


}
