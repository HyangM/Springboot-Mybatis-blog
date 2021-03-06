package com.hm.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.hm.springboot.aop.SessionIntercepter;

@Configuration
public class WebConfig implements WebMvcConfigurer{ 
	
	@Value("${file.path}")
	private String fileRealPath;
	
//	@Autowired
//	private SessionIntercepter sessionIntercepter;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		// 파일 경로 인식하기
		registry.addResourceHandler("/media/**")
			.addResourceLocations("file:///"+fileRealPath)
			.setCachePeriod(3600)
			.resourceChain(true)
			.addResolver(new PathResourceResolver());
			;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SessionIntercepter())
		.addPathPatterns("/user/profile/**")
		.addPathPatterns("/post/write/**")
		.addPathPatterns("/post/update/**")
		.addPathPatterns("/post/delete/**");
	}
	// addexcludePathPatterns() 제외 시킬 때 사용!!
}