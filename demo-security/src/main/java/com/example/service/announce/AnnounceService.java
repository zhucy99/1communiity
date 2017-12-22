package com.example.service.announce;

import org.springframework.data.domain.Page;

import com.example.entity.Comment;
import com.example.entity.announce.Announce;

public interface AnnounceService{
	Page<Announce> findAll(int page, int size);
	public Page<Announce> findSearch(Announce model,int page, int size);
	Page<Announce> delete(Announce model,int page, int size);
	public Announce findByName(String name);
	public Announce findById(Long id);
	public Page<Comment> addComment(Comment comment);
}
