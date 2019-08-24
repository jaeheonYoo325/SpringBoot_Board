package com.springboot.project.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import com.springboot.project.interceptor.SessionInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	private static final String CLASSPATH_RESOURCE_LOCATIONS = "classpath:static/";
	
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver(){
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setDefaultEncoding("UTF-8");
		commonsMultipartResolver.setMaxUploadSizePerFile(10 * 1024 * 1024); //10 * 1024 * 1024 (10MB)
		return commonsMultipartResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/css/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "css/");
		registry.addResourceHandler("/js/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "js/");
		registry.addResourceHandler("/images/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "images/");
		registry.addResourceHandler("/bootstrap/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "bootstrap/");		
		registry.addResourceHandler("/ckeditor5/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "ckeditor5/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionInterceptor())
				.addPathPatterns("/board/boardList.do");		
	}
	
}
