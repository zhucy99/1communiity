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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.SysRole;
import com.example.entity.SysUser;
import com.example.repository.SysRoleRepository;
import com.example.repository.SysUserRepository;
import com.example.service.RoleService;
import com.example.service.UserService;
import com.example.util.Others;

@Service
public class UserServiceImp implements UserDetailsService, UserService {
	@Autowired
	SysUserRepository userRepository;
	@Autowired
	RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		SysUser user = userRepository.findByUsername(s);
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		System.out.println("s:" + s);
		System.out.println("username:" + user.getUsername() + ";password:" + user.getPassword());
		return user;
	}

	public Page<SysUser> findAll(int page, int size) {
		Sort sort = new Sort(Direction.ASC, "id");
	    Pageable pageable = new PageRequest(page-1, size, sort);
		return userRepository.findAll(pageable);
	}

	@Override
	public Page<SysUser> findSearch(SysUser model,int page, int size) {
		Sort sort = new Sort(Direction.ASC, "id");
	    Pageable pageable = new PageRequest(page-1, size, sort);
		Page<SysUser> result = userRepository.findAll(new Specification<SysUser>() {
			
			@Override
			public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (model.getUsername()!=null) {
					list.add(cb.like(root.get("username").as(String.class), "%" + model.getUsername() + "%"));
				}
				
				
				if (model.getMail() != null) {
					list.add(cb.equal(root.get("mail").as(String.class), model.getMail()));
				}

				/*

				if (StringUtils.isNotBlank(model.getTelPhone())) {
					list.add(cb.like(root.get("telPhone").as(String.class), "%" + model.getTelPhone() + "%"));
				}

				if (model.getDepartment() != null && model.getDepartment().getCode() != null) {
					list.add(cb.equal(root.get("department").as(DepartmentModel.class), model.getDepartment()));
				}*/

				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}

		},pageable);

		return result;
	}

	@Override
	public Page<SysUser> add(SysUser user) {
		this.userRepository.save(user);
		
		return this.findAll(1,10);
		
	}

	@Override
	public Page<SysUser> delete(SysUser model,int page, int size) {
		this.userRepository.delete(model);
		return findSearch(model, page, size);
	}

	@Override
	public int addWithRole(SysUser user) {
		SysRole role = this.roleService.findByName("ROLE_USER");
		if(role!=null) {
			List<SysRole> roles = user.getRoles();
			if(roles==null) {
				roles = new ArrayList<SysRole>();
				roles.add(role);
				user.setRoles(roles);
			}else {
				roles.add(role);
			}
			user.setPassword(Others.MD5Encode(user.getPassword()));
			this.userRepository.save(user);
			return 1;
		}else {
			return -1;
		}
		
	}

	@Override
	public boolean isMailOrUsernameNotUsed(SysUser user) {
		
		List<SysUser> result = userRepository.findAll(new Specification<SysUser>() {
			
			@Override
			public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (user.getUsername()!=null) {
					list.add(cb.like(root.get("username").as(String.class), user.getUsername()));
				}
				
				
				if (user.getMail() != null) {
					list.add(cb.equal(root.get("mail").as(String.class), user.getMail()));
				}

				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}

		});
		
		if(result.size()==0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public SysUser findById(Long id) {
		return this.userRepository.getOne(id);
	}

}
