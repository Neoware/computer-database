package com.excilys.formation.service;

import java.util.List;

public interface Service<T> {

	List<T> getSelection(int offset, int limit);

	int count();
}
