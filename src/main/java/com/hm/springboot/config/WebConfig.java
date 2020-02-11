package com.hm.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

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
}