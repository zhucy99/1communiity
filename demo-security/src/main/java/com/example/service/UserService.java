package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.entity.SysUser;

public interface UserService extends UserDetailsService{
	Page<SysUser> findAll(int page, int size);
	public Page<SysUser> findSearch(SysUser model,int page, int size);
	Page<SysUser> add(SysUser user);
	int addWithRole(SysUser user);
	Page<SysUser> delete(SysUser model,int page, int size);
}
