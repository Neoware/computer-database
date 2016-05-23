package com.excilys.formation.utils;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.entity.Company;

public class CompanyMapper {

	public static CompanyDTO FromEntityToDto(Company toConvert) {
		CompanyDTO companyDTO = null;
		if (toConvert.getName() != null) {
			companyDTO = new CompanyDTO();
			companyDTO.setId(toConvert.getId());
			companyDTO.setName(toConvert.getName());
		}
		return companyDTO;
	}

	public static Company FromDtoToEntity(CompanyDTO toConvert) {
		Company company = null;
		if (toConvert.getName() != null) {
			company = new Company(toConvert.getId(), toConvert.getName());
		}
		return company;
	}
}
