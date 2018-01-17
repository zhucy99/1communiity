package com.example.service.imp.announce;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.entity.SysUser;
import com.example.entity.announce.Category;
import com.example.repository.announce.CategoryRepository;
import com.example.service.announce.CategoryService;
import com.example.util.Others;

@Service
public class CategoryServiceImp implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	HttpServletRequest request;
	
	@Override
	public Page<Category> findAll(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Category> findSearch(Category model, int page, int size) {

		Sort sort = new Sort(Direction.ASC, "id");
		Pageable pageable = new PageRequest(page - 1, size, sort);
		Page<Category> result = categoryRepository.findAll(new Specification<Category>() {

			@Override
			public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (model.getParent() != null) {
					list.add(cb.equal(root.get("parent").as(Category.class),  model.getParent()));
				}

				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}

		}, pageable);
		return result;
	}

	@Override
	public void add(Category category) {
		
		SysUser user = Others.getCurrentUser();
		categoryRepository.save(category);
		
	}

	@Override
	public Page<Category> delete(Category model, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Category findById(Long id) {
		return this.categoryRepository.findOne(id);
	}

	@Override
	public Page<Category> addAndSearch(Category category) {
		this.add(category);
		return this.findSearch(category, 1, 5);
	}

	@Override
	public List<Category> findByParentCode(String code) {
		Category cat = new Category();
		cat.setCode(code);
		
		
		
		List<Category> result = categoryRepository.findAll(new Specification<Category>() {

			@Override
			public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (cat.getCode() != null) {
					list.add(cb.like(root.get("code").as(String.class),  cat.getCode()));
				}

				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}

		});
		if(result.size()==1) {
			return result.get(0).getChildren();
		}else {
			return null;
		}
		
	}
	

}
