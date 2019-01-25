package com.wu.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.wu.domain.Article;

public interface ArticleDao {
	public List<Article> list();

	public Integer getTotalCount(DetachedCriteria detachedCriteria);

	public List<Article> getPageData(DetachedCriteria detachedCriteria, Integer index, Integer pageSize);

	public void delete(Article article);

	public Article edit(Article article);

	public void save(Article article);

	public void update(Article article);
}
