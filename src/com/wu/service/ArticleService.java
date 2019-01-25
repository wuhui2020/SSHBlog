package com.wu.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.wu.domain.Article;
import com.wu.domain.PageBean;

public interface ArticleService {
	public List<Article> list();

	public PageBean getList(DetachedCriteria detachedCriteria, Integer currPage, int pageSize);

	public void delete(Article article);

	public Article edit(Article article);

	public void save(Article article);

	public void update(Article article);
}
