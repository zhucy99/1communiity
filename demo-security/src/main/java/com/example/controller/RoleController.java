package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.SysUser;
import com.example.service.imp.UserServiceImp;

@Controller
@RequestMapping(value = "/role")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RoleController {

	@Autowired
	UserServiceImp customUserService;

	/*
	 * @RequestMapping("/manage") public String findAll(Model model) { List<SysUser>
	 * users = customUserService.findAll(); model.addAttribute("users", users);
	 * return "user/userManage"; }
	 */

	@RequestMapping("/manage")
	public String findAll() {
		return "user/userManage";
	}

	@RequestMapping(value = "/search", method = { RequestMethod.GET })
	public @ResponseBody Page<SysUser> searchByName(@ModelAttribute("user") SysUser user,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		Page<SysUser> users = customUserService.findSearch(user, page, size);
		return users;
	}

	@RequestMapping(value = "/all", method = { RequestMethod.GET })
	public @ResponseBody Page<SysUser> all(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {

		Page<SysUser> data = customUserService.findAll(page, size);

		return data;
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public @ResponseBody List<SysUser> add(@ModelAttribute("user") SysUser user) {
		return this.add(user);
	}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.GET })
	public @ResponseBody Page<SysUser> delete(@ModelAttribute("user") SysUser user,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return this.customUserService.delete(user, page, size);
	}
}
