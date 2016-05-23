package com.excilys.formation.service;

import java.util.List;

public interface Service<T> {

	public List<T> getSelection(int offset, int limit);
}
