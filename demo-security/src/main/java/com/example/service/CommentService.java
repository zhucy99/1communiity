package com.example.service;

import org.springframework.data.domain.Page;
import com.example.entity.Comment;

public interface CommentService{
	Page<Comment> findAll(int page, int size);
	public Page<Comment> findSearch(Comment model,int page, int size);
	void add(Comment comment);
	Page<Comment> addAndSearch(Comment comment);
	Page<Comment> delete(Comment model,int page, int size);
	public Comment findByName(String name);
	public Comment findById(Long id);
}
