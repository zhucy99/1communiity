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
import com.example.entity.SysRole;
import com.example.service.RoleService;

@Controller
@RequestMapping(value = "/role")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RoleController {

	@Autowired
	RoleService roleService;
	
	@RequestMapping(value = "/search", method = { RequestMethod.GET })
	public @ResponseBody Page<SysRole> searchByName(@ModelAttribute("role") SysRole role,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		Page<SysRole> roles = this.roleService.findSearch(role, page, size);
		return roles;
	}

	@RequestMapping(value = "/all", method = { RequestMethod.GET })
	public @ResponseBody Page<SysRole> all(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {

		Page<SysRole> data = this.roleService.findAll(page, size);

		return data;
	}

	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public @ResponseBody List<SysRole> add(@ModelAttribute("role") SysRole role) {
		return this.add(role);
	}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.GET })
	public @ResponseBody Page<SysRole> delete(@ModelAttribute("role") SysRole role,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return this.roleService.delete(role, page, size);
	}
}
