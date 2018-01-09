package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Msg;
import com.example.entity.SysUser;
import com.example.service.imp.UserServiceImp;
import com.example.util.Others;

@Controller
@RequestMapping(value = "/myAccount")
@PreAuthorize("hasRole('ROLE_USER')")
public class myAccountController {

	@Autowired
	UserServiceImp customUserService;

	@RequestMapping("")
	public String myAccount(@RequestParam(value = "content", defaultValue = "profil") String content,@RequestParam(value = "id", required=false) Long id, Model model) {
		model.addAttribute("content", content);
		if("profil".equals(content)) {
			model.addAttribute("id", Others.getCurrentUser().getId());
		}else {
			model.addAttribute("id", id);
		}
		return "myAccount/main";
	}

}