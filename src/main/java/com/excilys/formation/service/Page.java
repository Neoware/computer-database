package com.excilys.formation.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Page<T> {

	private static final Logger LOG = LoggerFactory.getLogger(Page.class);
	private int limit;
	private List<T> currentPageElements;
	private int current;
	private int count;
	private int totalPage;

	public Page() {
	}

	public Page(List<T> elements, PageRequest pageRequest, int count) {
		this.currentPageElements = elements;
		this.limit = pageRequest.getLimit();
		this.current = pageRequest.getPage();
		this.count = count;
		if (count % limit == 0) {
			totalPage = count / limit;
		} else {
			totalPage = count / limit + 1;
		}
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<T> getCurrentPageElements() {
		return currentPageElements;
	}

	public void setCurrentPageElements(List<T> currentPageElements) {
		this.currentPageElements = currentPageElements;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}
