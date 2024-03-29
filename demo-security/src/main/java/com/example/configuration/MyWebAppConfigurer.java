package com.example.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by 18274 on 2017/8/9.
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/userImg/**").addResourceLocations("file:/C:/image/");
        super.addResourceHandlers(registry);
    }
}
