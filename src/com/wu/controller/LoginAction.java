package com.wu.controller;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wu.domain.User;
import com.wu.service.LoginService;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class LoginAction extends ActionSupport implements ModelDriven<User>{
	private User user = new User();
	private LoginService loginService;
	public void setLoginService(LoginService loginService){
		this.loginService = loginService;
	}
	
	@Override
	public User getModel() {
		return user;
	}
	public String login(){
//		System.out.println(user);
		User res = loginService.login(user);
//		System.out.println(res);
		if(res == null){
			//错误信息回显
			this.addActionError("用户名或者密码错误");
			return LOGIN;
		}else{
			//保存用户
			ActionContext.getContext().getSession().put("curUse",res);
			return SUCCESS;
		}
	}
	
	
	public String signout() throws IOException{
		ActionContext.getContext().getSession().remove("curUse");
		//ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		//ServletActionContext.getResponse().getWriter().println("{\"res\":\"OK\"}");
		return "login_out";
	}
	
//	public String init() throws IOException{
//		User user = (User)ActionContext.getContext().getSession().get("curUse");
//		System.out.println(user);
//		JSONArray jsonArray = JSONArray.fromObject(user,new JsonConfig());
//		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
//		ServletActionContext.getResponse().getWriter().println(jsonArray);
//		return null;
//	}
	
}
