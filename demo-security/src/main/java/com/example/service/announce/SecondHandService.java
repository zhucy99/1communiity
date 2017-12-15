package com.example.service.announce;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.SysUser;
import com.example.entity.announce.Announce;
import com.example.entity.announce.SecondHand;

public interface SecondHandService{
	Page<SecondHand> findAll(int page, int size);
	public Page<SecondHand> findSearch(SecondHand model,int page, int size);
	Page<SecondHand> add(SecondHand secondHand, SysUser user, MultipartFile[] file);
	Page<SecondHand> delete(SecondHand model,int page, int size);
	public SecondHand findByName(String name);
	public SecondHand findById(Long id);
}
