package com.excilys.formation.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.entity.Company;

/**
 * Mapper for the Company objects.
 * 
 * @author neoware
 *
 */
public class CompanyMapper {

	/**
	 * Convert an entity company to a DTO company
	 * 
	 * @param toConvert
	 *            The entity that need to be converted.
	 * @return The DTO generated from the entity.
	 */
	public static CompanyDTO fromEntityToDto(Company toConvert) {
		CompanyDTO companyDTO = null;
		if (toConvert.getName() != null) {
			companyDTO = new CompanyDTO();
			companyDTO.setId(Long.toString(toConvert.getId()));
			companyDTO.setName(toConvert.getName());
		}
		return companyDTO;
	}

	/**
	 * Convert a DTO company to an entity company
	 * 
	 * @param toConvert
	 *            the DTO that need to be converted.
	 * @return The entity generated from the DTO.
	 */
	public static Company fromDtoToEntity(CompanyDTO toConvert) {
		Company company = null;
		if (toConvert.getName() != null) {
			company = new Company(Long.parseLong(toConvert.getId()), toConvert.getName());
		}
		return company;
	}

	public static List<CompanyDTO> fromEntitiesToDtos(List<Company> companies) {
		List<CompanyDTO> companyDTOs = new ArrayList<>();
		for (Company company : companies) {
			companyDTOs.add(fromEntityToDto(company));
		}
		return companyDTOs;
	}
}
