package com.example.configuration;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

@Configuration
public class Captcha {
	@Bean
	public DefaultKaptcha captchaProducer(){
		DefaultKaptcha captchaProducer =new DefaultKaptcha();
		Properties properties =new Properties();
		properties.setProperty("kaptcha.border","no");
		properties.setProperty("kaptcha.textproducer.font.color","blue");
		properties.setProperty("kaptcha.image.width","125");
		properties.setProperty("kaptcha.image.height","45");
		properties.setProperty("kaptcha.textproducer.font.size","45");
		properties.setProperty("kaptcha.session.key","code");
		properties.setProperty("kaptcha.textproducer.char.length","4");
		properties.setProperty("kaptcha.textproducer.font.names","宋体,楷体,微软雅黑");
		Config config=new Config(properties);
		captchaProducer.setConfig(config);
		return  captchaProducer;
	}

}
