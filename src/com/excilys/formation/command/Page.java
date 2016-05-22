package com.excilys.formation.command;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.service.ComputerService;
import com.excilys.formation.service.Service;

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
	 * @param computerService The list of elements that need to be paginated
	 */
	public Page(Service computerService){
		this.offset = 0;
		this.pageSize = 30;
		this.currentPageElements = new ArrayList<>(computerService.subList(offset , offset + pageSize));
		this.nbPage = computerService.size() / pageSize;
		this.currentPage = 0;
		this.currentPageElements = computerService.subList(offset , offset + pageSize);
	}

	/**
	 * Method that move the page instance to the next page if it's possible
	 */
	public boolean next(){
		if (currentPage < nbPage) {
			currentPage++;
			offset = currentPage * pageSize;
			int limit;
			if (currentPage == nbPage)
				limit = elements.size() % pageSize;
			else
				limit = pageSize;
			currentPageElements = elements.subList(offset, offset + limit);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Method that move the page instance to the previous page if it's possible
	 */
		public boolean previous(){
			if (currentPage > 0) {
				currentPage--;
				offset = currentPage * pageSize;
				currentPageElements = elements.subList(offset, offset + pageSize);
				return true;
			}
			else {
				return false;
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
