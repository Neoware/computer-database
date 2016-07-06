package com.excilys.formation.service.interfaces;

import java.util.List;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.entity.Company;
import com.excilys.formation.persistence.PageRequest;
import com.excilys.formation.service.Page;

public interface CompanyService {

	Company getById(Long id);

	List<Company> getAll();

	int count();

	Page<CompanyDTO> getPage(PageRequest pageRequest);

	void delete(Long id);

}