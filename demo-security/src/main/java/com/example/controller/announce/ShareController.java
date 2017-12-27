package com.example.controller.announce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.entity.announce.Share;
import com.example.service.imp.announce.ShareServiceImp;
import com.example.util.Others;

@Controller
@RequestMapping(value = "/share")
@PreAuthorize("hasRole('ROLE_USER')")
public class ShareController {

	@Autowired
	ShareServiceImp shareService;


	@RequestMapping(value = "")
	public String share(@RequestParam(value = "content", defaultValue = "list") String content,@RequestParam(value = "id", required=false) Long id, Model model) {
		model.addAttribute("content", content);
		model.addAttribute("id", id);
		return "announce/share/main";
	}
	
	@RequestMapping(value = "detail")
	public String detail(@RequestParam(value = "id") Long id, Model model) {
		Share share = shareService.findById(id);
		model.addAttribute("data", share);
		return "announce/share/detail";
	}
	
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public String add(@ModelAttribute("share") Share share) {
		this.shareService.add(share);
		return "redirect:/share";
	}
	
	@RequestMapping(value = "/search", method = { RequestMethod.GET })
	public @ResponseBody Page<Share> searchByName(@ModelAttribute("share") Share share,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "12") int size) {
		Page<Share> shares = shareService.findSearch(share, page, size);
		return shares;
	}
	
	@RequestMapping(value = "/findById", method = { RequestMethod.GET })
	public @ResponseBody Share findById(@RequestParam(value = "id") Long id) {
		Share share = shareService.findById(id);
		return share;
	}
	
}
