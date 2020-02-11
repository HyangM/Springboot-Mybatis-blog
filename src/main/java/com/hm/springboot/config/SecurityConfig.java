package com.hm.springboot.config;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hm.springboot.model.RespCM;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)	// 진입 직전에 실행
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder encode() {	// BCrypt type은 hash함수. hash는 복호화가 안되는 암호화
		return new BCryptPasswordEncoder();
	}	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		//모든 요청을 받는다
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/user/profile/**",
						 "/post/write/**",
						 "/post/update/**",
						 "/post/delete/**",
						 "/post/detail/**").authenticated()
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")		// session만 필요한게 아니라 권한도 필요하다는 의미
			.anyRequest().permitAll()
		.and()
			.formLogin()
			.loginPage("/user/login")		// GET
			.loginProcessingUrl("/user/loginProc")	// POST만 낚아챔
			.successHandler(new AuthenticationSuccessHandler() {

				@Override
				public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) throws IOException, ServletException {
					
					PrintWriter out = response.getWriter();
					
					ObjectMapper mapper = new ObjectMapper();
					
					// string 으로 저장
					String jsonString = mapper.writeValueAsString(new RespCM(200,"ok"));
					System.out.println("jsonString : "+jsonString);
					out.print(jsonString);
					out.flush();
				}
			}); // SuccessUrl 사용할 수 있음
//			.defaultSuccessUrl("/"); // successHandler를 사용할 수 있음
									 // 로그인에서는 return 타입이 json인데 defaultSuccessURl("/")를 하면 /로 호출하는 것임. 
									 // json데이터가 아니여서 jsp에서 parse 오류 발생해서 로그인이 안됐음 

		/*
		 * .failureHandler(new AuthenticationFailureHandler() {
		 * 
		 * @Override public void onAuthenticationFailure(HttpServletRequest request,
		 * HttpServletResponse response, AuthenticationException exception) throws
		 * IOException, ServletException { request.getSession(); response.toString();
		 * exception.getMessage(); System.out.println("실패");
		 * 
		 * } })
		 */
	}
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(encode());
	}

}
