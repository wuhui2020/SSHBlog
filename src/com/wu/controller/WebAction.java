package com.wu.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;
import com.wu.domain.Article;
import com.wu.domain.Category;
import com.wu.domain.PageBean;
import com.wu.service.ArticleService;
import com.wu.service.CategoryService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class WebAction extends ActionSupport{
	private CategoryService categoryService;
	private ArticleService articleService;
	private Integer parentid;
	
	public void setCategoryService(CategoryService categoryService){
		this.categoryService = categoryService;
	}
	public void setArticleService(ArticleService articleService){
		this.articleService = articleService;
	}
	public void setParentid(Integer parentid){
		this.parentid = parentid;
	}
	//获取分类
	public void getcategory() throws IOException{
//		System.out.println(parentid);
		List<Category> list = categoryService.getCategory(parentid);
//		System.out.println(list);
		JSONArray jsonArray = JSONArray.fromObject(list,new JsonConfig());
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray);
		
		
	}
	//获取文章
	private Integer currPage;
	private Integer cid;
	public void setCurrPage(Integer currPage){
		this.currPage = currPage;
	}
	
	public void setCid(Integer cid){
		this.cid = cid;
	}
	public void getarticle() throws IOException{
//		System.out.println("currPage==="+currPage);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
		if(parentid != null){
			List<Category> list = categoryService.getCategory(parentid);
			Object[] obj = new Object[list.size()];
			for(int i=0;i<list.size();i++){
				Integer cid = list.get(i).getCid();
				obj[i] = cid;
			}
			detachedCriteria.add(Restrictions.in("category.cid", obj));
		}
		if(cid != null){
			detachedCriteria.add(Restrictions.eq("category.cid", cid));
		}
		PageBean pageBean = articleService.getList(detachedCriteria, currPage, 5);
		
		//延时加载  因为关联其它表的数据
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
		
		JSONObject jsonObject = JSONObject.fromObject(pageBean,jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonObject.toString());
		
		System.out.println(pageBean);
		
	}
	private Integer article_id;
	public void setArticle_id(Integer article_id){
		this.article_id = article_id;
	}
	public void getonearticle() throws IOException{
		Article article = new Article();
		article.setArticle_id(article_id);
		Article article2 = articleService.edit(article);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});
		
		JSONObject jsonArray = JSONObject.fromObject(article2,jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
	}
	
}
