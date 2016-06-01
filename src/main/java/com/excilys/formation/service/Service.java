package com.excilys.formation.service;

public interface Service<T> {

	Page<T> getPage(PageRequest pageRequest);

	int count();
}
