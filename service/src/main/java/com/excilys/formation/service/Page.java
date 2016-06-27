package com.excilys.formation.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.persistence.PageRequest;

/**
 * 
 * Class containing entities and meta-informations about the page that will be
 * displayed in the view.
 * 
 * @author neoware
 *
 * @param <T>
 *            The type of entites that will be hold in the page
 */
public class Page<T> {

	private static final Logger LOG = LoggerFactory.getLogger(Page.class);
	private int limit;
	private List<T> currentPageElements;
	private int current;
	private int count;
	private int totalPage;
	private String search;
	private String sort;
	private String order;

	public Page() {
	}

	/**
	 * Setting all parameters for the page, including the totalPage that is
	 * calculated here.
	 * 
	 * @param elements
	 *            The list of elements that will be contained in the page
	 * @param pageRequest
	 *            The pageRequest containing all informations from the user
	 *            request from the view.
	 * @param count
	 *            The total count of elements that are in the database
	 */
	public Page(List<T> elements, PageRequest pageRequest, int count) {
		this.currentPageElements = elements;
		this.limit = pageRequest.getLimit();
		this.current = pageRequest.getPage();
		this.search = pageRequest.getSearch();
		this.sort = pageRequest.getSort();
		this.order = pageRequest.getOrder();
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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Page [limit=" + limit + ", currentPageElements=" + currentPageElements + ", current=" + current
				+ ", count=" + count + ", totalPage=" + totalPage + ", search=" + search + ", sort=" + sort + ", order="
				+ order + "]";
	}

}
