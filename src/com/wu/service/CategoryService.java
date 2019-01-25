package com.wu.service;

import java.util.List;

import com.wu.domain.Category;

public interface CategoryService {
	public void save(Category category);
	public List<Category> categoryQuery();
	public List<Category> categoryone(int cid);
	public void update(Category category);
	public void delete(Category category);
	public List<Category> getCategory(Integer parentid);
}
