package com.example.controller.announce;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Comment;
import com.example.entity.SysUser;
import com.example.entity.announce.Announce;
import com.example.service.imp.CommentServiceImp;
import com.example.service.imp.announce.AnnounceServiceImp;
import com.example.util.Others;

@Controller
@RequestMapping(value = "/announce")
@PreAuthorize("hasRole('ROLE_USER')")
public class AnnounceController {

	@Autowired
	AnnounceServiceImp announceService;
	
	@Autowired
	CommentServiceImp commentService;
	

	@RequestMapping(value = "/search", method = { RequestMethod.GET })
	public @ResponseBody Page<Announce> searchByName(@ModelAttribute("announce") Announce announce,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "showAll", defaultValue = "false", required= false) boolean showAll, HttpServletRequest request) {
		if(!showAll) {
			announce.setAuthor(Others.getCurrentUser());
		}
		
		Page<Announce> announces = announceService.findSearch(announce, page, size);
		return announces;
	}
	
	@RequestMapping(value = "/findById", method = { RequestMethod.GET })
	public @ResponseBody Announce findById(@RequestParam(value = "id") Long id) {
		Announce announces = announceService.findById(id);
		return announces;
	}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.GET })
	public @ResponseBody Page<Announce> delete(@ModelAttribute("announce") Announce announce,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return this.announceService.delete(announce, page, size);
	}
	
	@RequestMapping(value = "/addComment", method = { RequestMethod.GET })
	public @ResponseBody Page<Comment> addComment(
			@ModelAttribute("comment") Comment comment) {
		Page<Comment> page = this.announceService.addComment(comment);
		return page;
	}
	
	@RequestMapping(value = "/getComments", method = { RequestMethod.GET })
	public @ResponseBody Page<Comment> getComments(
			@ModelAttribute(value = "comment") Comment comment,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "5") int size) {
		Page<Comment> pager = this.commentService.findSearch(comment, page, size);
		return pager;
	}
	
}
