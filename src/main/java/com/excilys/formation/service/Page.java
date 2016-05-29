package com.excilys.formation.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Page<T> {

	private static final Logger LOG = LoggerFactory.getLogger(Page.class);
	private int limit;
	private List<T> currentPageElements;
	private int current;
	private int count;

	public Page() {
	}
	
	public Page(List<T> elements, PageRequest pageRequest){
		currentPageElements = elements;
	}
	
	
}
