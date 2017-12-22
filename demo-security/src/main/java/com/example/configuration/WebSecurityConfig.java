package com.example.configuration;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.exception.CaptchaException;
import com.example.filter.ValidateCodeAuthenticationFilter;
import com.example.service.imp.UserServiceImp;
import com.example.util.Others;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	UserDetailsService customUserService() {
		return new UserServiceImp();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		 auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder(){

	            @Override
	            public String encode(CharSequence rawPassword) {
	                return Others.MD5Encode((String)rawPassword);
	            }

	            @Override
	            public boolean matches(CharSequence rawPassword, String encodedPassword) {
	                return encodedPassword.equals(Others.MD5Encode((String)rawPassword));
	            }}); //user Details Service验证
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		ValidateCodeAuthenticationFilter loginAuthenticationFilter = new ValidateCodeAuthenticationFilter();
		loginAuthenticationFilter.setAuthenticationManager(authenticationManager());
		loginAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());

		http.addFilterBefore(loginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).authorizeRequests().antMatchers("/signup", "/signup/**", "/signup**", "/welcome", "/**", "/kaptcha/*")
				.permitAll().and().authorizeRequests().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").failureUrl("/login?error").permitAll().and()
				// 开启cookie保存用户数据
				.rememberMe()
				// 设置cookie有效期
				.tokenValiditySeconds(60 * 60 * 24 * 7)
				// 设置cookie的私钥
				.key("chongyang").and().logout().permitAll();
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		ExceptionMappingAuthenticationFailureHandler failureHandler = new ExceptionMappingAuthenticationFailureHandler();
		Map<String, String> failureUrlMap = new HashMap<>();
		failureUrlMap.put(CaptchaException.class.getName(), "/login");
		/*
		failureUrlMap.put(BadCredentialsException.class.getName(), LoginAuthenticationFailureHandler.PASS_ERROR_URL);
		failureUrlMap.put(CaptchaException.class.getName(), LoginAuthenticationFailureHandler.CODE_ERROR_URL);
		failureUrlMap.put(AccountExpiredException.class.getName(), LoginAuthenticationFailureHandler.EXPIRED_URL);
		failureUrlMap.put(LockedException.class.getName(), LoginAuthenticationFailureHandler.LOCKED_URL);
		failureUrlMap.put(DisabledException.class.getName(), LoginAuthenticationFailureHandler.DISABLED_URL);*/
		failureHandler.setExceptionMappings(failureUrlMap);
		return failureHandler;
	}

}
