package com.excilys.formation.model;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

	private List<T> elements;
	private int pageSize;
	private int nbPage;
	private int currentPage;
	private List<T> currentPageElements;
	private int offset;

	public Page() {}
	
	/**
	 * Constructor that should be use to pass the elements to offer page capability
	 * @param elements The list of elements that need to be paginated
	 */
	public Page(List <T> elements){
		this.elements = new ArrayList<T>(elements);
		this.offset = 0;
		this.pageSize = 30;
		if (elements.size() < pageSize)
			this.pageSize = elements.size();
		this.currentPageElements = new ArrayList<T>(elements.subList(offset , offset + pageSize));
		this.nbPage = elements.size() / pageSize;
		this.currentPage = 0;
		this.currentPageElements = elements.subList(offset , offset + pageSize);
	}

	/**
	 * Method that move the page instance to the next page if it's possible
	 */
	public void next(){
		if (currentPage < nbPage)
		{
			currentPage++;
			offset = currentPage * pageSize;
			int limit;
			if (currentPage == nbPage)
				limit = elements.size() % pageSize;
			else
				limit = pageSize;
			currentPageElements = elements.subList(offset, offset + limit);
		}
	}

	/**
	 * Method that move the page instance to the previous page if it's possible
	 */
		public void previous(){
			if (currentPage > 0)
			{
				currentPage--;
				offset = currentPage * pageSize;
				currentPageElements = elements.subList(offset, offset + pageSize);
			}
		}
		
		/**
		 * Method to print the content of the current position of the page instance
		 */
		public void printPage(){
			for (T current : currentPageElements){
				System.out.println(current);
			}
		}

		public List<T> getElements() {
			return elements;
		}

		public void setElements(List<T> elements) {
			this.elements = elements;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public int getNbPage() {
			return nbPage;
		}

		public void setNbPage(int nbPage) {
			this.nbPage = nbPage;
		}

		public int getCurrentPage() {
			return currentPage;
		}

		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
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
