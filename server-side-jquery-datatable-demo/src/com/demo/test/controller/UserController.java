package com.demo.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.test.entity.datatable.PaginationCriteria;
import com.demo.test.entity.datatable.TablePage;
import com.demo.test.paginator.TablePaginator;

@Controller
public class UserController {
	
	@Autowired
	private TablePaginator paginator;

	@GetMapping("/")
	public String getAllUsers(Model model) {
		
		return "allUsers";
	}
	
	@PostMapping("/users/data")
	public @ResponseBody TablePage saveUser(@RequestBody PaginationCriteria paginationCriteria) {
		System.out.println("/users/data");
		System.out.println("pagination criteria : "+paginationCriteria.toString());
		return paginator.getPage(paginationCriteria);
		
	}
	/*
	 * @GetMapping("/user/create") public String createUser(Model model) {
	 * model.addAttribute("user", new User()); return "createUser"; }
	 */
	
}
