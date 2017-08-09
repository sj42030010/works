package com.xh.media.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LogoutController{

	@RequestMapping("/logout")
	protected ModelAndView handleRequestInternal(HttpServletRequest req,
			HttpServletResponse arg1) throws Exception {
		ModelAndView mv = new ModelAndView();
		System.out.println("logout is run!!!! ");
		req.getSession().removeAttribute("userInfo");
		req.getSession().removeAttribute("username");
		req.getSession().removeAttribute("groups");
		mv.setViewName("login");
	    return mv;
	}


		
}
