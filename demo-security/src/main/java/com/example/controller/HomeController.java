package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Msg;
import com.example.entity.SysUser;
import com.example.entity.announce.SecondHand;
import com.example.exception.CaptchaException;
import com.example.service.announce.SecondHandService;
import com.example.service.imp.UserServiceImp;
import com.google.code.kaptcha.Constants;

@Controller
public class HomeController {

	@Autowired
	UserServiceImp customUserService;
	
	@Autowired
	SecondHandService secondHandService;

	@RequestMapping({ "/", "/welcome" })
	public String welcome(Model model) {
		Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
		
		Page<SecondHand> res = secondHandService.findSearch(new SecondHand(), 1, 3);
		model.addAttribute("secondHands", res.getContent());
		
		return "index";
	}
	
	@RequestMapping("/admin")
	public String admin(Model model) {
		Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
		model.addAttribute("msg", msg);
		return "admin/admin";
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
		model.addAttribute("msg", msg);
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/signup")
	public String signup() {
		return "signup";
	}

	@RequestMapping(value = "/signup/create", method = { RequestMethod.POST })
	public String signup(@ModelAttribute("user") SysUser user, @RequestParam(value = "code") String code,HttpSession session) {
		String sessionCode = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);  
		if(code.equals(sessionCode)) {
			this.customUserService.addWithRole(user);
			return "redirect:/signupOk";
		}else {
			throw new CaptchaException("code not ok");
		}
		
	}
	
	@RequestMapping(value = "/signup/isUsernameNotUsed", method = { RequestMethod.GET })
	public @ResponseBody boolean isUsernameNotUsed(@ModelAttribute("user") SysUser user) {
		boolean res = this.customUserService.isMailOrUsernameNotUsed(user);
		return res;
	}

	@RequestMapping("/signupOk")
	public String signupOk() {
		return "signupOk";
	}

}