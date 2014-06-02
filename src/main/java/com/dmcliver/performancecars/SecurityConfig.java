package com.dmcliver.performancecars;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	public void configure(HttpSecurity sec) throws Exception{		
		sec.authorizeRequests().antMatchers("/**/*").permitAll();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.userDetailsService(buildUserDetailsService()).passwordEncoder(buildPasswordEncoder());
	}
	
//	@Bean(name = "passwordEncoder")
//	public PasswordEncoder buildPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean(name = "userDetailsService")
//	public UserDetailsService buildUserDetailsService() {
//		return new CustomUserDetailsService();
//	}
}
