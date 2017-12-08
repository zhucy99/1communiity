package com.example.service;

import org.springframework.data.domain.Page;

import com.example.entity.Announce;
import com.example.entity.SysUser;

public interface AnnounceService{
	Page<Announce> findAll(int page, int size);
	public Page<Announce> findSearch(Announce model,int page, int size);
	Page<Announce> add(Announce announce, SysUser user);
	Page<Announce> delete(Announce model,int page, int size);
	public Announce findByName(String name);
}
