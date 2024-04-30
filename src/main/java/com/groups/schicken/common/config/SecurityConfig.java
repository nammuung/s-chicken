package com.groups.schicken.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.groups.schicken.Employee.EmployeeService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private SecurityLoginSucessHandler handler;

	@Autowired
	private SecurityLoginFailHandler failHandler;

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web
				.ignoring()
				.requestMatchers("/css/**")
				.requestMatchers("/js/**")
				.requestMatchers("/vendor/**")
				.requestMatchers("/img/**")
				.requestMatchers("/html/**");
//				.requestMatchers("/firebase/**");
	}



	@Bean
	SecurityFilterChain filterChain (HttpSecurity security) throws Exception {


		security
				//CSRF 추가
				.csrf(
						(csrf) ->
									csrf.disable()
				)
				.authorizeHttpRequests(
						(authorizeRequests)->
											authorizeRequests
											.requestMatchers("/", "/login**", "/employee/**").authenticated()
//											.requestMatchers("/").permitAll()
											.requestMatchers("employee/role").hasRole("ADMIN")
											.requestMatchers("employee/list").hasRole("PERSONNEL_WRITER")
											.anyRequest().permitAll()
											)
////											.requestMatchers("/").permitAll()
//											.requestMatchers("/employee/join2").permitAll()
//											.anyRequest().permitAll()
//
//						)// 권한 끝 부분

				.formLogin(
							(login)->
									login
									.loginPage("/employee/login")
									.successHandler(handler)
									.usernameParameter("id") // username이 아니라 id임
									//.defaultSuccessUrl("/")	// 성공했을때 localhost 로 이동
									.failureHandler(failHandler)

									.permitAll()


						)// 로그인 끝부분
				.logout(
							(logout)->
									logout
										.logoutRequestMatcher(new AntPathRequestMatcher("/employee/logout"))
										.logoutSuccessUrl("/employee/login")
										.invalidateHttpSession(true) // 로그아웃 성공시 session만료
										.permitAll()
						)// 로그아웃 끝 부분

				.rememberMe(
								(rememberMe)->
										rememberMe
										.rememberMeParameter("rememberMe")
										.tokenValiditySeconds(600)
										.key("rememberMe")
										.userDetailsService(employeeService)
										.authenticationSuccessHandler(handler)
										.useSecureCookie(false)

						)// remember 끝 부분
				.sessionManagement(
						(sessionManagement)->
							sessionManagement
								.maximumSessions(1)
								.maxSessionsPreventsLogin(false)
								.expiredUrl("/expired")

				)//sessionManagement 끝
				.oauth2Login(
						(oauth2Login)-> 
							oauth2Login.userInfoEndpoint(
									(ue)->ue.userService(employeeService)
							)
				)//oauth2Login 끝부분
				
				
				;	
		
		
		
		return security.build();
	}






}
