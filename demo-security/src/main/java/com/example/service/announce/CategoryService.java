package com.example.service.announce;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.entity.announce.Category;

public interface CategoryService{
	Page<Category> findAll(int page, int size);
	public Page<Category> findSearch(Category model,int page, int size);
	void add(Category category);
	Page<Category> addAndSearch(Category category);
	Page<Category> delete(Category model,int page, int size);
	public Category findByName(String name);
	public Category findById(Long id);
	public List<Category> findByParentCode(String code);
}
