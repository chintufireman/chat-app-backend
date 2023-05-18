package com.chatapp.config;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://192.168.1.9:3000","http://localhost:3000")
		.allowedMethods("GET","POST","DELETE","PUT")
		.allowedHeaders("*")
		.exposedHeaders("*")
		.allowCredentials(true);
	}
	
	/*
	 * The allowedHeaders property of CORS configuration specifies which HTTP
	 * headers can be used in a cross-origin request. The browser will only allow
	 * these headers to be included in the request, and any additional headers will
	 * be blocked.
	 * 
	 * The exposedHeaders property, on the other hand, specifies the headers that
	 * the server can expose to the client in the response. This means that these
	 * headers will be made available to the client even if they are not listed in
	 * the response headers.
	 * 
	 * In summary, allowedHeaders specifies which headers can be included in the
	 * request, while exposedHeaders specifies which headers can be exposed to the
	 * client in the response.
	 */

}
