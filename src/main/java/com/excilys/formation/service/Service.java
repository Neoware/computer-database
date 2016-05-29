package com.excilys.formation.service;

import java.util.List;

public interface Service<T> {

	List<T> getPage(PageRequest pageRequest);

	int count();
}
