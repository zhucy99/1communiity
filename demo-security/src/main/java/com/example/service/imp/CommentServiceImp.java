package com.example.service.imp;

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

import com.example.entity.Comment;
import com.example.entity.SysUser;
import com.example.entity.announce.Announce;
import com.example.repository.CommentRepository;
import com.example.service.CommentService;
import com.example.util.Others;

@Service
public class CommentServiceImp implements CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	HttpServletRequest request;
	
	@Override
	public Page<Comment> findAll(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Comment> findSearch(Comment model, int page, int size) {

		Sort sort = new Sort(Direction.ASC, "id");
		Pageable pageable = new PageRequest(page - 1, size, sort);
		Page<Comment> result = commentRepository.findAll(new Specification<Comment>() {

			@Override
			public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (model.getAnnounce() != null) {
					list.add(cb.equal(root.get("announce").as(Announce.class),  model.getAnnounce()));
				}

				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}

		}, pageable);

		return result;
	}

	@Override
	public void add(Comment comment) {
		
		SysUser user = Others.getCurrentUser();
		comment.setCreateTime(new Date());
		comment.setAuthor(user);
		commentRepository.save(comment);
		
	}

	@Override
	public Page<Comment> delete(Comment model, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Comment findById(Long id) {
		return this.commentRepository.findOne(id);
	}

	@Override
	public Page<Comment> addAndSearch(Comment comment) {
		this.add(comment);
		return this.findSearch(comment, 1, 5);
	}

}
