package com.jsp.shoppingcart.controller;


import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.shoppingcart.dao.CustomerDao;
import com.jsp.shoppingcart.dto.Customer;
import com.jsp.shoppingcart.dto.Merchant;


@Controller
public class CustomerController {
	@Autowired
	CustomerDao cdao;
	@RequestMapping("/addCustomer")
	public ModelAndView addCustomer() {
		Customer c=new Customer();

		ModelAndView mav = new ModelAndView();
		mav.addObject("customerobj", c);
		mav.setViewName("customerform");

		return mav;
	}
	@RequestMapping("/savecustomer")
	public ModelAndView savecustomer(@ModelAttribute("customerobj") Customer c) {
		cdao.saveCustomer(c);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "Registered successfull");
		mav.setViewName("customerloginform");
		return mav;
		}

	  @RequestMapping("/customerloginvalidation")
	  public ModelAndView loginValidation(ServletRequest req,HttpSession session)
	  {
		  String email = req.getParameter("email");
		  String password =req.getParameter("password");
		  
		  Customer c=cdao.login(email, password);
		  if(c!=null) {
			  ModelAndView mav=new ModelAndView();
			  mav.addObject("msg","Login successfully");
			  mav.setViewName("customeroptions");
			  session.setAttribute("customerinfo", c);
			  return mav;
		  }
		  else {
			  ModelAndView mav=new ModelAndView();
			  mav.addObject("msg","invalid credentials");
			  mav.setViewName("customerloginform");
			  return mav;
		  }
	  }
	  
	}
   		
	

