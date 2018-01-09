package com.example.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.example.entity.Msg;
import com.example.entity.SysUser;
import com.example.entity.announce.SecondHand;
import com.example.exception.CaptchaException;
import com.example.service.announce.SecondHandService;
import com.example.service.imp.UserServiceImp;
import com.example.util.enums.LocaleLanguage;
import com.google.code.kaptcha.Constants;

@Controller
public class HomeController {

	@Autowired
	UserServiceImp customUserService;

	@Autowired
	SecondHandService secondHandService;
	
	@Autowired 
	SessionLocaleResolver localeResolver;

	@RequestMapping({ "/", "/welcome" })
	public String welcome(Model model) {
		Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");

		Page<SecondHand> res = secondHandService.findSearch(new SecondHand(), 1, 3);
		model.addAttribute("secondHands", res.getContent());

		return "index";
	}
	
	@RequestMapping({"/welcome2" })
	public String welcome2(Model model) {
		Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");

		Page<SecondHand> res = secondHandService.findSearch(new SecondHand(), 1, 4);
		model.addAttribute("secondHands", res.getContent());

		return "index2";
	}

	@RequestMapping({ "/changeLanguage" })
	public String changeLanguage(@RequestParam(value = "code", required=false) String code, Model model) {
		Locale locale = LocaleLanguage.getLocaleByCode(code);
		if(locale!=null) {
			localeResolver.setDefaultLocale(locale);
		}

		return "redirect:/welcome";
	}

	@RequestMapping("/admin")
	public String admin(@RequestParam(value = "content", defaultValue = "user") String content,
			@RequestParam(value = "id", required = false) Long id, Model model) {
		model.addAttribute("content", content);
		model.addAttribute("id", id);
		return "admin/main";
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
	public String signup(@ModelAttribute("user") SysUser user, @RequestParam(value = "code") String code,
			HttpSession session) {
		String sessionCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (code.equals(sessionCode)) {
			this.customUserService.addWithRole(user);
			return "redirect:/signupOk";
		} else {
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