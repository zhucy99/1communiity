package com.example.service;

import org.springframework.data.domain.Page;

import com.example.entity.SysRole;

public interface RoleService{
	Page<SysRole> findAll(int page, int size);
	public Page<SysRole> findSearch(SysRole model,int page, int size);
	Page<SysRole> add(SysRole user);
	Page<SysRole> addWithRole(SysRole user);
	Page<SysRole> delete(SysRole model,int page, int size);
	public SysRole findByName(String name);
}
