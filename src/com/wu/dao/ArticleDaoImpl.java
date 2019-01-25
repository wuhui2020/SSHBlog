package com.wu.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.wu.domain.Article;

public class ArticleDaoImpl extends HibernateDaoSupport implements ArticleDao {

	@Override
	public List<Article> list() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
		List<Article> findByCriteria = (List<Article>)getHibernateTemplate().findByCriteria(detachedCriteria);
		return findByCriteria;
	}

	@Override
	public Integer getTotalCount(DetachedCriteria detachedCriteria) {
		
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> count = (List<Long>)getHibernateTemplate().findByCriteria(detachedCriteria);
		if(count.size() >0){
			return count.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<Article> getPageData(DetachedCriteria detachedCriteria, Integer index, Integer pageSize) {
		detachedCriteria.setProjection(null);
		List<Article> list = (List<Article>)getHibernateTemplate().findByCriteria(detachedCriteria,index,pageSize);
		return list;
	}

	@Override
	public void delete(Article article) {
		
		getHibernateTemplate().delete(article);
	}

	@Override
	public Article edit(Article article) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
		detachedCriteria.add(Restrictions.eq("article_id", article.getArticle_id()));
		List<Article> list = (List<Article>)getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void save(Article article) {
		getHibernateTemplate().save(article);
		
	}

	@Override
	public void update(Article article) {
		getHibernateTemplate().update(article);
		
	}

}
