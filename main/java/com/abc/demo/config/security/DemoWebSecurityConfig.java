package com.abc.demo.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.abc.demo.config.handler.DemoAuthenticationEntryPoint;
import com.abc.demo.config.handler.DemoAuthenticationFailureHandler;
import com.abc.demo.config.handler.DemoAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class DemoWebSecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .and()
        .formLogin()        
        .loginProcessingUrl("/login") //Postman設定27~34
        .usernameParameter("username")
        .passwordParameter("password")
        .successHandler( new DemoAuthenticationSuccessHandler() )
        .failureHandler( new DemoAuthenticationFailureHandler() )
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(new DemoAuthenticationEntryPoint())
        .and()
        .csrf()
        .ignoringRequestMatchers("/login");

		 return http.build();
		
	}
	
	@Bean
    public UserDetailsService userDetailsService() {
        
        UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user123")
                                .password("user123")
                                .roles("USER").build());
        
        manager.createUser(users.username("admin123")
                                .password("admin123")
                                .roles("USER", "ADMIN")
                                .build());
        
        return manager;
    }
	
}
