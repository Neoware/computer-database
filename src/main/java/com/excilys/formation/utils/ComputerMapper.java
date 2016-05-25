package com.excilys.formation.utils;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.entity.Company;
import com.excilys.formation.entity.Computer;

public class ComputerMapper {

	public static ComputerDTO FromEntityToDto(Computer toConvert) {
		ComputerDTO computerDTO = null;
		if (toConvert.getName() != null) {
			computerDTO = new ComputerDTO();
			computerDTO.setId(toConvert.getId());
			computerDTO.setName(toConvert.getName());
			computerDTO.setIntroduced(DateUtils.localDateToString(toConvert.getIntroduced()));
			computerDTO.setDiscontinued(DateUtils.localDateToString(toConvert.getDiscontinued()));
			computerDTO.setCompanyId(toConvert.getComputerCompany().getId());
			computerDTO.setCompanyName(toConvert.getComputerCompany().getName());
		}
		return computerDTO;
	}

	public static Computer FromDtoToEntity(ComputerDTO toConvert) {
		Computer computer = null;
		Company company = null;
		if (toConvert.getName() != null) {
			if (toConvert.getCompanyId() == 0) {
				company = new Company(null, toConvert.getCompanyName());
				System.out.println("null id");
			} else {
				company = new Company(toConvert.getCompanyId(), toConvert.getCompanyName());
			}
			computer = new Computer.ComputerBuilder(toConvert.getName()).id(toConvert.getId())
					.introduced(DateUtils.stringToLocalDate(toConvert.getIntroduced()))
					.discontinued(DateUtils.stringToLocalDate(toConvert.getDiscontinued())).computerCompany(company)
					.build();
		}
		return computer;
	}
}
