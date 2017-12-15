package com.example.controller.announce;

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
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.SysUser;
import com.example.entity.announce.SecondHand;
import com.example.service.imp.announce.SecondHandServiceImp;

@Controller
@RequestMapping(value = "/secondHand")
@PreAuthorize("hasRole('ROLE_USER')")
public class SecondHandController {

	@Autowired
	SecondHandServiceImp secondHandService;


	@RequestMapping(value = "")
	public String secondHand() {

		return "announce/secondHand/main";
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public String add(@ModelAttribute("secondHand") SecondHand secondHand,@RequestParam("file") MultipartFile[] files) {
		SysUser user = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.secondHandService.add(secondHand,user,files);
		return "redirect:/secondHand";
	}
	
	@RequestMapping(value = "/search", method = { RequestMethod.GET })
	public @ResponseBody Page<SecondHand> searchByName(@ModelAttribute("secondHand") SecondHand secondHand,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "12") int size) {
		Page<SecondHand> secondHands = secondHandService.findSearch(secondHand, page, size);
		return secondHands;
	}
	
	@RequestMapping(value = "/findById", method = { RequestMethod.GET })
	public @ResponseBody SecondHand findById(@RequestParam(value = "id") Long id) {
		SecondHand secondHands = secondHandService.findById(id);
		return secondHands;
	}
	
}
