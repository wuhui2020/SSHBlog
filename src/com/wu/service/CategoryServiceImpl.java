package com.wu.service;

import java.util.List;

import com.wu.dao.CategoryDao;
import com.wu.domain.Category;

public class CategoryServiceImpl implements CategoryService {
	private CategoryDao categoryDao;
	public void setCategoryDao(CategoryDao categoryDao){
		this.categoryDao = categoryDao;
	}
	public void save(Category category) {
		categoryDao.save(category);
		
	}
	@Override
	public List<Category> categoryQuery() {
		System.out.println("service");
		return categoryDao.categoryQuery();
	}
	@Override
	public List<Category> categoryone(int cid) {
		
		return categoryDao.categoryone(cid);
	}
	@Override
	public void update(Category category) {
		categoryDao.update(category);
		
	}
	@Override
	public void delete(Category category) {
		categoryDao.delete(category);
		
	}
	@Override
	public List<Category> getCategory(Integer parentid) {
		List<Category> category = categoryDao.getCategory(parentid);
		return category;
	}

}
