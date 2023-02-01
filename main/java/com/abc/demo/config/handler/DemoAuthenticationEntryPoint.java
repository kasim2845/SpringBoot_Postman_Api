package com.abc.demo.config.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.abc.demo.config.dto.DemoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DemoAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		 response.setStatus(HttpStatus.UNAUTHORIZED.value()); 
	        DemoResponse data = new DemoResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.name());
	        response.getOutputStream().println(new ObjectMapper().writeValueAsString(data));
		
	}

}
