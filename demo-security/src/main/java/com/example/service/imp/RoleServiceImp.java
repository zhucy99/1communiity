package com.example.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.example.entity.SysRole;
import com.example.repository.SysRoleRepository;
import com.example.service.RoleService;

@Service
public class RoleServiceImp implements RoleService {
	@Autowired
	SysRoleRepository roleRepository;

	public Page<SysRole> findAll(int page, int size) {
		Sort sort = new Sort(Direction.ASC, "id");
	    Pageable pageable = new PageRequest(page-1, size, sort);
		return roleRepository.findAll(pageable);
	}

	@Override
	public SysRole findByName(String name) {
		
		
		
		List<SysRole> result = roleRepository.findAll(new Specification<SysRole>() {
			
			@Override
			public Predicate toPredicate(Root<SysRole> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (name!=null) {
					list.add(cb.like(root.get("name").as(String.class), name));
				}
				
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}

		});
		if(result.size()==1) {
			return result.get(0);
		}else {
			return null;
		}
		
	}

	@Override
	public Page<SysRole> findSearch(SysRole model, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SysRole> add(SysRole user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SysRole> addWithRole(SysRole user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SysRole> delete(SysRole model, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
