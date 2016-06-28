package com.excilys.formation.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.entity.Company;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.util.DateUtils;

/**
 * Mapper for the computer Objects.
 * 
 * @author neoware
 *
 */
public class ComputerMapper {

	/**
	 * Convert an entity computer to a DTO computer
	 * 
	 * @param toConvert
	 *            The entity that need to be converted.
	 * @return The DTO generated from the entity.
	 */
	public static ComputerDTO fromEntityToDto(Computer toConvert) {
		ComputerDTO computerDTO = null;
		if (toConvert.getName() != null) {
			computerDTO = new ComputerDTO();
			computerDTO.setId(Long.toString(toConvert.getId()));
			computerDTO.setName(toConvert.getName());
			computerDTO.setIntroduced(DateUtils.localDateToString(toConvert.getIntroduced()));
			computerDTO.setDiscontinued(DateUtils.localDateToString(toConvert.getDiscontinued()));
			if (toConvert.getComputerCompany() != null) {
				computerDTO.setCompanyId(Long.toString(toConvert.getComputerCompany().getId()));
				computerDTO.setCompanyName(toConvert.getComputerCompany().getName());
			}
		}
		return computerDTO;
	}

	/**
	 * Convert a DTO computer to an entity computer
	 * 
	 * @param toConvert
	 *            the DTO that need to be converted.
	 * @return The entity generated from the DTO.
	 */
	public static Computer fromDtoToEntity(ComputerDTO toConvert) {
		Computer computer = null;
		Company company = null;
		if (toConvert.getName() != null) {
			if (toConvert.getCompanyId() == null) {
				company = new Company(null, toConvert.getCompanyName());
			} else {
				company = new Company(Long.parseLong(toConvert.getCompanyId()), toConvert.getCompanyName());
			}
			computer = Computer.getBuilder().name(toConvert.getName())
					.introduced(DateUtils.stringToLocalDate(toConvert.getIntroduced()))
					.discontinued(DateUtils.stringToLocalDate(toConvert.getDiscontinued())).computerCompany(company)
					.build();
			if (toConvert.getId() != null) {
				computer.setId(Long.parseLong(toConvert.getId()));
			}
		}
		return computer;
	}

	public static List<ComputerDTO> fromEntitiesToDtos(List<Computer> computers) {
		List<ComputerDTO> computerDtos = new ArrayList<>();
		for (Computer computer : computers) {
			computerDtos.add(fromEntityToDto(computer));
		}
		return computerDtos;
	}
}
