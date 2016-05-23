package com.excilys.formation.command;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.service.Service;

public class Page<T> {

	private static final Logger LOG = LoggerFactory.getLogger(Page.class);
	private int pageSize;
	private List<T> currentPageElements;
	private int offset;
	private Service<T> service;

	public Page() {}
	
	/**
	 * Constructor that should be use to pass the elements to offer page capability
	 * @param service The service that will be use to retrieve the pages.
	 */
	public Page(Service<T> service){
		this.offset = 0;
		this.pageSize = 30;
		this.service = service;
		this.currentPageElements = service.getSelection(offset, pageSize);
	}

	/**
	 * Method that move the page instance to the next page if it's possible
	 */
	public boolean next(){
		if (this.currentPageElements.size() == pageSize){
			this.offset += pageSize;
			this.currentPageElements = service.getSelection(offset, pageSize);
			return true;
		}
		return false;
		
	}

	/**
	 * Method that move the page instance to the previous page if it's possible
	 */
		public boolean previous(){
			if (offset <= 0){
				return false;
			}
			else
			{
				LOG.debug("trying to go to the previous page");
				this.offset -= pageSize;
				this.currentPageElements = service.getSelection(offset, pageSize);
				return true;
			}
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


		
	}
