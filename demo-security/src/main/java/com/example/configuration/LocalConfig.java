package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class LocalConfig {
	@Bean(name = "localeResolver")
	public SessionLocaleResolver localeResolverBean() {
		return new SessionLocaleResolver();
	}
	// @Bean(name="messageSource")
	// public ResourceBundleMessageSource resourceBundleMessageSource(){
	// ResourceBundleMessageSource source=new ResourceBundleMessageSource();
	// source.setBasename("messages");
	// return source;
	// }
}
