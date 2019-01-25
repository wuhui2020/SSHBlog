package com.wu.controller;

import com.opensymphony.xwork2.ActionSupport;

public class JumpAction extends ActionSupport{
	
	public String top(){
		return "top";
	}
	public String left(){
		return "left";
	}
	public String account(){
		return "account";
	}
	public String add(){
		return "add";
	}
}
