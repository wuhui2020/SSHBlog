package com.wu.controller;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.xml.internal.messaging.saaj.soap.ver1_1.Detail1_1Impl;
import com.wu.domain.Article;
import com.wu.domain.PageBean;
import com.wu.service.ArticleService;

public class ArticleAction extends ActionSupport implements ModelDriven<Article>{
	private ArticleService articleService;
	private Article article = new Article();
	public File upload;//上传文件
	public String uploadContentType;//文件类型 
	public String uploadFileName;//文件名称
	public void setUpload(File upload){
		this.upload = upload;
	}
	public void setUploadContentType(String uploadContentType){
		this.uploadContentType = uploadContentType;
	}
	public void setUploadFileName(String uploadFileName){
		this.uploadFileName = uploadFileName;
	}
	@Override
	public Article getModel() {
		// TODO Auto-generated method stub
		return article;
	}
	
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	public String list(){
//		System.out.println("article");
		List<Article> list = articleService.list();
		ActionContext.getContext().getValueStack().set("article", list);
//		System.out.println(list);
		return "list";
	}
	
	private Integer currPage;
	private String keyWord;
	public void setCurrPage(Integer currPage){
		this.currPage = currPage;
	}
	public void setKeyWord(String keyWord){
		this.keyWord = keyWord;
	}
	public String pageList(){
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
		if(keyWord !=null){
			detachedCriteria.add(Restrictions.like("article_title", "%"+keyWord+"%"));
		}
		PageBean pageBean = articleService.getList(detachedCriteria,currPage,5);
//		System.out.println(pageBean);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "list";
	}
	
	
	public String add() throws IOException{
		if(upload != null){
			//设置文件上路径
			String path = ServletActionContext.getServletContext().getRealPath("/upload");
			//一个目录下不能存放相同文件名，随机生成
			//获取方文件扩展名
			int lio = uploadFileName.lastIndexOf(".");
			String txtname = uploadFileName.substring(lio);
			//uuid随机生成文件名  + 文件扩展名
			String uuidfilename = UUID.randomUUID().toString().replace("-", "")+txtname;
//			System.out.println(uuidfilename);
			File file = new File(path);
			//判断有没有这个文件夹 没有就创建
//			System.out.println("==="+file.exists());
			if(!file.exists()){
				System.out.println("con");
				file.mkdirs();
			}
			//文件上传
			File dicFile = new File(path+"/"+uuidfilename);
//			System.out.println(dicFile);
			FileUtils.copyFile(upload, dicFile);
			article.setArticle_pic(uuidfilename);
		}
		article.setArticle_time(new Date().getTime());
//		System.out.println(article);
		articleService.save(article);
		return "reslist";
	}
	
	public String edit(){
		Article list = articleService.edit(article);
		ActionContext.getContext().getValueStack().push(list);
		return "edit";
	}
	
	public String updata() throws IOException{
//		System.out.println("upload======"+upload);
		if(upload != null){
			String path = ServletActionContext.getServletContext().getRealPath("/upload");
			int lastIndexOf = uploadFileName.lastIndexOf(".");
			String txt = uploadFileName.substring(lastIndexOf);
			
			String newfilename = UUID.randomUUID().toString().replace("-", "")+txt;
//			System.out.println("newfilename=="+newfilename);
			File file = new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			File dec = new File(path+"/"+newfilename);
			FileUtils.copyFile(upload, dec);
			article.setArticle_pic(newfilename);
		}
		article.setArticle_time(System.currentTimeMillis());
		
//		System.out.println(article);
		articleService.update(article);
		return "reslist";
	}
	public String delete(){
		articleService.delete(article);
		return "reslist";
	}

}
