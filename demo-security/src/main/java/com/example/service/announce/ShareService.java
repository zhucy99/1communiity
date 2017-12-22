package com.example.service.announce;

import org.springframework.data.domain.Page;
import com.example.entity.announce.Share;

public interface ShareService{
	Page<Share> findAll(int page, int size);
	public Page<Share> findSearch(Share model,int page, int size);
	Page<Share> add(Share share);
	Page<Share> delete(Share model,int page, int size);
	public Share findByName(String name);
	public Share findById(Long id);
}
