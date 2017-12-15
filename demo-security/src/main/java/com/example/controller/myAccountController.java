package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entity.Msg;
import com.example.entity.SysUser;
import com.example.service.imp.UserServiceImp;

@Controller
@RequestMapping(value = "/myAccount")
@PreAuthorize("hasRole('ROLE_USER')")
public class myAccountController {

	@Autowired
	UserServiceImp customUserService;

	@RequestMapping("")
	public String myAccount() {
		
		return "myAccount/main";
	}

}