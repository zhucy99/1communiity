package com.example.service.imp;

import java.util.ArrayList;
import java.util.Date;
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

import com.example.entity.Announce;
import com.example.entity.SysRole;
import com.example.entity.SysUser;
import com.example.repository.AnnounceRepository;
import com.example.repository.SysRoleRepository;
import com.example.service.AnnounceService;
import com.example.service.RoleService;

@Service
public class AnnounceServiceImp implements AnnounceService {
	@Autowired
	AnnounceRepository announceRepository;

	@Override
	public Page<Announce> findAll(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Announce> findSearch(Announce model, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Announce> add(Announce announce, SysUser user) {
		announce.setCreateTime(new Date());
		announce.setAuthor(user);
		announce.setType(Announce.TYPE_SECONDHAND);
		announce.setStatus(Announce.STATUS_CREATE);
		announceRepository.save(announce);
		return null;
	}

	@Override
	public Page<Announce> delete(Announce model, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Announce findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
