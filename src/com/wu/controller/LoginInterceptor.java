package com.wu.controller;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wu.domain.User;

public class LoginInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("curUse");
		System.out.println(user);
		if(user == null){
			//没有登入
			ActionSupport action = (ActionSupport)actionInvocation.getAction();
			action.addActionError("没有登录，没有权限访问");
			return "login";
		}else{
			//对请求的方法放行
			return actionInvocation.invoke();
		}
		
		
		
	}

}
