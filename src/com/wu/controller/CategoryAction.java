package com.wu.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wu.domain.Category;
import com.wu.service.CategoryService;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class CategoryAction extends ActionSupport implements ModelDriven<Category> {
	private Category category =  new Category();
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService){
		this.categoryService = categoryService;
	}
	@Override
	public Category getModel() {
		return category;
	}

	public String add(){
//		System.out.println(category);
		categoryService.save(category);
		
		return "listAction";
	}
	
	public String list(){
//		System.out.println("aa");
		List<Category> categorylist= categoryService.categoryQuery();
//		System.out.println(categorylist);
		ActionContext.getContext().getValueStack().set("category", categorylist);
		return "list";
	}
	
	public String categoryone() throws IOException{
//		System.out.println(category.getCid());
		List<Category> categorylist = categoryService.categoryone(category.getCid());
		//返回json格式数据
		JSONArray object = JSONArray.fromObject(categorylist, new JsonConfig());
		
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(object.toString());
		
		return null;
	}
	public String update(){
		try {
			String cname = URLDecoder.decode(category.getCname(), "utf-8");
			category.setCname(cname);
			categoryService.update(category);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "listAction";
	}
	
	public String delete(){
//		System.out.println(category);
		categoryService.delete(category);
		return "listAction";
	}
	
	//获取分类
	public void getAllCategory() throws IOException{
		List<Category> list = categoryService.getCategory(category.getParentid());
		JSONArray jsonArray = JSONArray.fromObject(list,new JsonConfig());
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray);
//		System.out.println(list);
	}
	
}
