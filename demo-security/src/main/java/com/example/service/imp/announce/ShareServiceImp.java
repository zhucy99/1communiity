package com.example.service.imp.announce;

import java.util.ArrayList;
import java.util.Date;
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
import com.example.entity.announce.Announce;
import com.example.entity.announce.Share;
import com.example.repository.announce.ShareRepository;
import com.example.service.announce.ShareService;
import com.example.util.Others;

@Service
public class ShareServiceImp implements ShareService {
	
	@Autowired
	ShareRepository shareRepository;
	
	@Autowired
	HttpServletRequest request;
	
	@Override
	public Page<Share> findAll(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Share> findSearch(Share model, int page, int size) {

		Sort sort = new Sort(Direction.ASC, "id");
		Pageable pageable = new PageRequest(page - 1, size, sort);
		Page<Share> result = shareRepository.findAll(new Specification<Share>() {

			@Override
			public Predicate toPredicate(Root<Share> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (model.getTitle() != null) {
					list.add(cb.like(root.get("title").as(String.class), "%" + model.getTitle() + "%"));
				}

				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}

		}, pageable);

		return result;
	}

	@Override
	public Page<Share> add(Share share) {
		
		SysUser user = Others.getCurrentUser();
		
		share.setCreateTime(new Date());
		share.setAuthor(user);
		share.setType(Announce.TYPE_SHARE);
		share.setStatus(Announce.STATUS_CREATE);
		shareRepository.save(share);
		return null;
	}

	@Override
	public Page<Share> delete(Share model, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Share findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Share findById(Long id) {
		return this.shareRepository.findOne(id);
	}

}
