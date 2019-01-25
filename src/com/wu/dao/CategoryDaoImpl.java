package com.wu.dao;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.velocity.components.DateDirective;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.ReactiveAdapter;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wu.domain.Category;
@Transactional
public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao {

	@Override
	public void save(Category category){
		getHibernateTemplate().save(category);
	}

	@Override
	public List<Category> categoryQuery() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Category.class);
		List<Category> list = (List<Category>)getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size() > 0){
			return list;
		}
		return null;
	}

	@Override
	public List<Category> categoryone(int cid) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Category.class);
		detachedCriteria.add(Restrictions.eq("cid", cid));
		List<Category> categorylist = (List<Category>)getHibernateTemplate().findByCriteria(detachedCriteria);
		if(categorylist.size() > 0){
			return categorylist;
		}
		return null;
	}

	@Override
	public void update(Category category) {
		getHibernateTemplate().update(category);
		
	}

	@Override
	public void delete(Category category) {
//		System.out.println("dao"+category);
		getHibernateTemplate().delete(category);
	}

	@Override
	public List<Category> getCategory(Integer parentid) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Category.class);
		detachedCriteria.add(Restrictions.eq("parentid", parentid));
		List<Category> findByCriteria = (List<Category>)getHibernateTemplate().findByCriteria(detachedCriteria);
		return findByCriteria;
	}

}
