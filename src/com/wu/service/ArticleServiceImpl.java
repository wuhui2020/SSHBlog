package com.wu.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.wu.dao.ArticleDao;
import com.wu.domain.Article;
import com.wu.domain.PageBean;

@Transactional
public class ArticleServiceImpl implements ArticleService {
	private ArticleDao articleDao;
	public void setArticleDao(ArticleDao articleDao){
		this.articleDao = articleDao;
	}
	@Override
	public List<Article> list() {
		return articleDao.list();
	}
	@Override
	public PageBean getList(DetachedCriteria detachedCriteria, Integer currPage, int pageSize) {
		PageBean<Article> pageBean = new PageBean<>();
		//设置当前页
		pageBean.setCurrentPage(currPage);
		//设置当前页显示多少条数据
		pageBean.setPageSize(pageSize);
		//获取总记录数
		Integer totalCount = articleDao.getTotalCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		pageBean.setTotalPage(pageBean.getTotalPage());
		
		//查询当前页的数据
		List<Article> list = articleDao.getPageData(detachedCriteria,pageBean.getIndex(),pageBean.getPageSize());
		pageBean.setList(list);
		return pageBean;
		
	}
	@Override
	public void delete(Article article) {
		articleDao.delete(article);
		
	}
	@Override
	public Article edit(Article article) {
		
		return articleDao.edit(article);
	}
	@Override
	public void save(Article article) {
		articleDao.save(article);
		
	}
	@Override
	public void update(Article article) {
		articleDao.update(article);
		
	}

}
