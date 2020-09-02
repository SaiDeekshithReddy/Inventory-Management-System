package com.virtusa.demo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.virtusa.demo.bean.Users;
import com.virtusa.demo.repository.UsersRepo;

//Controller for login
@Controller
public class HomeController {
	
	@Autowired
	UsersRepo usersRepo;
	
	//Request for login page
	@RequestMapping("index")
	public String home() {
		return "home.jsp";
	}
	
	//Verifying whether the user login is valid or not
	@RequestMapping("verify")
	public ModelAndView verify(HttpServletRequest req ) {
		HttpSession session = req.getSession();
		ModelAndView mv = new ModelAndView();
		try {
			long id = Long.parseLong(req.getParameter("id"));
			String password = req.getParameter("password");
			if(usersRepo.existsById(id) && (usersRepo.findById(id).orElse(null)).getPassword().equals(password) ) {
				Users user = usersRepo.findById(id).orElse(null);
				session.setAttribute("user", user);
				session.setAttribute("name", user.getName());
				session.setAttribute("role", user.getRole());
				mv.setViewName("toppage.jsp");
				return mv;
				
			}
		}
		catch(Exception e) {
			mv.addObject("login_errormsg","Invalid Username or Password");
			mv.setViewName("home.jsp");
			return mv;
		}
		
		mv.addObject("login_errormsg","Invalid Username or Password");
		mv.setViewName("home.jsp");
		return mv;
	}
	
	@RequestMapping("logout")
	public String logOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:index";
	}
	
}
