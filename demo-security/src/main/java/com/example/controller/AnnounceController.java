package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Announce;
import com.example.entity.SysUser;
import com.example.service.imp.AnnounceServiceImp;
import com.example.service.imp.UserServiceImp;

@Controller
@RequestMapping(value = "/announce")
@PreAuthorize("hasRole('ROLE_USER')")
public class AnnounceController {

	@Autowired
	AnnounceServiceImp announceService;


	@RequestMapping(value = "/secondHand")
	public String secondHand() {

		return "announce/secondHand/main";
	}

	@RequestMapping(value = "/secondHand/add", method = { RequestMethod.POST })
	public String add(@ModelAttribute("announce") Announce announce) {
		SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.announceService.add(announce,user);
		return "redirect:/announce/secondHand";
	}
	
}
