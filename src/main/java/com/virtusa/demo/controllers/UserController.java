package com.virtusa.demo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.virtusa.demo.bean.Users;
import com.virtusa.demo.repository.UsersRepo;

@Controller
public class UserController {
	
	@Autowired
	UsersRepo usersRepo;
	
	//Request for features of user like create, delete...
	@RequestMapping("user_left")
	public ModelAndView userLeft() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user.jsp");
		return mv;
	}
	
	//Request for entering the details of new user
	@RequestMapping("create_user")
	public ModelAndView createUser() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("create_user.jsp");
		return mv;
	}
	
	//Creating the new user with the entered details
	@RequestMapping("add_user")
	public ModelAndView addUser(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("create_user.jsp");
		Users user = new Users();
		try {
			Long id = Long.parseLong(request.getParameter("user-id").trim());
			String name = request.getParameter("user-name").trim();
			String password = request.getParameter("user-password").trim();
			String role = request.getParameter("user-role").trim();
			if( id == null || name == "" || password == "" || role == "" ) {
				mv.addObject("user_created_msg_error", "error while creating user, invalid data");
				return mv;
			}
			if(usersRepo.existsById(id)) {
				mv.addObject("user_created_msg_error", "user already exists");
				return mv;
			}
		    user.setId(id);
		    user.setName(name);
		    user.setPassword(password);
		    user.setRole(role);
		    usersRepo.save(user);
		}
		catch(Exception e) {
			mv.addObject("user_created_msg_error", "error while creating user, invalid data");
			return mv;
		}
		mv.setViewName("create_user.jsp");
		mv.addObject("user_created_msg", "new user created");
		return mv;
	}
	
	//Request for entering details of the user to be removed
	@RequestMapping("delete_user")
	public ModelAndView deleteUser() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("delete_user.jsp");
		return mv;
	}
	
	//Removing the user
	@RequestMapping("remove_user")
	public ModelAndView removeUser(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("delete_user.jsp");
		try {
			long id = Long.parseLong(request.getParameter("user-id"));
			if(usersRepo.existsById(id)) {
				usersRepo.deleteById(id);
				mv.addObject("user_deleted_msg", "deleted user with id: "+id);
			}
			else {
				mv.addObject("user_deleted_msg_error", "invalid user id");
			}
		} catch (Exception e) {
			// TODO: handle exception
			mv.addObject("user_deleted_msg_error", "invalid user id");
		}
		return mv;
	}
}
