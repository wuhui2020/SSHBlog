package com.wu.dao;

import java.util.List;

import com.wu.domain.Category;

public interface CategoryDao {
	public void save(Category category);
	public List<Category> categoryQuery();
	public List<Category> categoryone(int cid);
	public void update(Category category);
	public void delete(Category category);
	public List<Category> getCategory(Integer parentid);
}
