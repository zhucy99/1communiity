package com.example.service.imp.announce;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContext;

import com.example.entity.Comment;
import com.example.entity.Picture;
import com.example.entity.SysRole;
import com.example.entity.SysUser;
import com.example.entity.announce.Announce;
import com.example.repository.CommentRepository;
import com.example.repository.SysRoleRepository;
import com.example.repository.announce.AnnounceRepository;
import com.example.repository.announce.SecondHandRepository;
import com.example.service.CommentService;
import com.example.service.RoleService;
import com.example.service.announce.AnnounceService;
import com.example.util.Others;
import com.example.util.enums.AnnounceStatus;
import com.example.util.enums.AnnounceType;

@Service
public class AnnounceServiceImp implements AnnounceService {

	@Autowired
	AnnounceRepository announceRepository;

	@Autowired
	SecondHandRepository secondHandRepository;

	@Autowired
	CommentService commentService;

	@Autowired
	HttpServletRequest request;

	@Override
	public Page<Announce> findAll(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Announce> findSearch(Announce model, int page, int size) {

		Sort sort = new Sort(Direction.ASC, "id");
		Pageable pageable = new PageRequest(page - 1, size, sort);
		Page<Announce> result = announceRepository.findAll(new Specification<Announce>() {

			@Override
			public Predicate toPredicate(Root<Announce> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (model.getTitle() != null) {
					list.add(cb.like(root.get("title").as(String.class), "%" + model.getTitle() + "%"));
				}

				if (model.getAuthor() != null && model.getAuthor().getId() != null) {
					list.add(cb.equal(root.get("author").as(SysUser.class), model.getAuthor()));
				}

				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}

		}, pageable);
		format(result.getContent());
		return result;
	}

	private void format(List<Announce> announces) {
		for (Announce announce : announces) {
			this.format(announce);
		}
	}

	private void format(Announce announce) {

		RequestContext requestContext = new RequestContext(request);
		SimpleDateFormat dt = new SimpleDateFormat(requestContext.getMessage("dateFormat"));
		announce.setCreateTimeStr(dt.format(announce.getCreateTime()));
		announce.setTypeStr(requestContext.getMessage(AnnounceType.getNameByCode(announce.getType())));
		announce.setStatusStr(requestContext.getMessage(AnnounceStatus.getNameByCode(announce.getStatus())));
	}

	@Override
	public Page<Announce> delete(Announce model, int page, int size) {
		this.announceRepository.delete(model.getId());
		return findSearch(model, page, size);
	}

	@Override
	public Announce findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Announce findById(Long id) {
		Announce announce = this.announceRepository.findOne(id);
		this.format(announce);
		return announce;
	}

	@Override
	public Page<Comment> addComment(Comment comment) {

		return this.commentService.addAndSearch(comment);

	}

}
