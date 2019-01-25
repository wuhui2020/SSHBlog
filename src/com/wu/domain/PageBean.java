package com.wu.domain;

import java.util.List;

public class PageBean<T> {
	private Integer currentPage;//当前页
	private Integer pageSize;   //一页多少条数据
	private Integer index;       //当前查询的角标
	private Integer totalCount;  //总记录数
	private Integer totalPage;   //总页数
	private List<T> list;
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		if(currentPage == null){
			currentPage = 1;
		}
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		if(pageSize == null){
			pageSize =5;
		}
		this.pageSize = pageSize;
	}
	/*计算当前页从数据库当中查询的位置*/
	public Integer getIndex() {
		System.out.println(currentPage);
		System.out.println(pageSize);
		return (currentPage - 1)*pageSize;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		double ceil = Math.ceil(totalCount*1.0/pageSize);
		return (int)ceil;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", pageSize=" + pageSize + ", index=" + index + ", totalCount="
				+ totalCount + ", totalPage=" + totalPage + ", list=" + list + "]";
	} 
	
}
