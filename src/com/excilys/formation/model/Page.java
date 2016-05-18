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

	public Page(List <T> elements){
		this.elements = new ArrayList<T>(elements);
		this.offset = 0;
		this.pageSize = 30;
		this.currentPageElements = new ArrayList<T>(elements.subList(offset , offset + pageSize));
		this.nbPage = elements.size() / pageSize;
		this.currentPage = 0;
		this.currentPageElements = elements.subList(offset , offset + pageSize);
	}

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

		public void previous(){
			if (currentPage > 0)
			{
				currentPage--;
				offset = currentPage * pageSize;
				currentPageElements = elements.subList(offset, offset + pageSize);
			}
		}
		
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
