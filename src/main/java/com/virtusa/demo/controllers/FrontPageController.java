package com.virtusa.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.virtusa.demo.bean.Users;
import com.virtusa.demo.repository.UsersRepo;

@Controller
public class FrontPageController {
	
	@Autowired
	UsersRepo usersRepo;
	
	//Request for warehouse of EMart
	@RequestMapping("warehousepage")
	public ModelAndView wareHouse() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("warehouseleft.jsp");
		return mv;
	}
	
	//Request for shelf of EMart
	@RequestMapping("shelf")
	public ModelAndView shelf() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("shelf_left.jsp");
		return mv;
	}
	
	//Request for changing user password
	@RequestMapping("change-password")
	public ModelAndView changePassword(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		String oldPassword = request.getParameter("old-password").trim();
		if(oldPassword.equals(user.getPassword())) {
			user.setPassword(request.getParameter("new-password").trim());
			usersRepo.save(user);
			mv.addObject("change_password_msg", "Password changed successfully");
		}
		else {
			mv.addObject("change_password_msgerror", "Entered wrong password");
		}
		mv.setViewName("profile.jsp");
		return mv;
	}
	
	//Request for user profile
	@RequestMapping("profile")
	public ModelAndView profile() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("profile.jsp");
		return mv;
	}
}
