package com.excilys.formation.command;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.entity.Computer;
import com.excilys.formation.service.Service;

public class Page<T> {

	private static final Logger LOG = LoggerFactory.getLogger(Page.class);
	private int pageSize;
	private List<T> currentPageElements;
	private int offset;
	private int pageNumber;
	private Service<T> service;
	private int totalPage;
	private int numberElements;

	public Page() {
	}

	/**
	 * Constructor that should be use to pass the elements to offer page
	 * capability
	 * 
	 * @param service
	 *            The service that will be use to retrieve the pages.
	 */
	public Page(Service<T> service) {
		this.pageNumber = 1;
		this.offset = 0;
		this.pageSize = 30;
		this.service = service;
		this.currentPageElements = service.getSelection(offset, pageSize);
	}

	public Page(Service<T> service, int pageSize) {
		this.offset = 0;
		this.pageNumber = 1;
		this.pageSize = pageSize;
		this.service = service;
		this.currentPageElements = service.getSelection(offset, pageSize);
	}

	/**
	 * Method that move the page instance to the next page if it's possible
	 */
	public boolean next() {
		if (this.currentPageElements.size() == pageSize) {
			this.offset += pageSize;
			this.currentPageElements = service.getSelection(offset, pageSize);
			pageNumber++;
			return true;
		}
		return false;
	}

	/**
	 * Method that move the page instance to the previous page if it's possible
	 */
	public boolean previous() {
		if (offset <= 0) {
			return false;
		} else {
			this.offset -= pageSize;
			this.currentPageElements = service.getSelection(offset, pageSize);
			pageNumber--;
			return true;
		}
	}

	public List<T> getPageElements(int pageRequest) {
		int elements = service.count();
		totalPage = elements / pageSize;
		List<T> content = new ArrayList<>();
		if (pageRequest <= totalPage) {
			offset = pageRequest * pageSize;
			content = service.getSelection(offset, pageSize);
		}
		return content;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getCurrentPageElements() {
		return currentPageElements;
	}

	public void setCurrentPageElements(List<T> currentPageElements) {
		this.currentPageElements = currentPageElements;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotalPage() {
		numberElements = service.count();
		totalPage = numberElements / pageSize;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getNumberElements() {
		numberElements = service.count();
		return numberElements;
	}

	public void setNumberElements(int numberElements) {
		this.numberElements = numberElements;
	}
}
