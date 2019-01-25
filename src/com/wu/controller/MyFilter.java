package com.wu.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;

public class MyFilter extends StrutsPrepareAndExecuteFilter{
	
	public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain) throws IOException, ServletException{
		//获取当前请求
		HttpServletRequest request =(HttpServletRequest)req;
		String requestURI = request.getRequestURI();
		if(requestURI.contains("js/ueditor/jsp/controller.jsp")){
			//放行
			chain.doFilter(req, res);
		}else{
			super.doFilter(req, res, chain);
		}
	
		
	}
	
}
