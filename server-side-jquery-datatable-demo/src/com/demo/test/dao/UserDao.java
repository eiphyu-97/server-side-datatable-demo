package com.demo.test.dao;

import java.util.List;

import com.demo.test.entity.User;
import com.demo.test.entity.datatable.OrderingCriteria;

public interface UserDao {
	
	void save(User user);
	List<User> getUsers();
	List<User> getUsersPage(int start,int length);
	
	List<User> getUserPageOrderByColumn(int start,int length, String colName, String dir);
	
}
