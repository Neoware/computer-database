package com.excilys.formation.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.entity.Company;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.util.CompanyMapper;
import com.excilys.formation.util.ComputerMapper;

/**
 * Servlet corresponding to the edit computer page.
 */
@Controller
public class EditComputer {
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;

	/**
	 * Get request loading the page for a specific computer, and load the list
	 * of companies from database.
	 */
	@RequestMapping(value = "/editcomputer", method = RequestMethod.GET)
	protected String displayEditableComputer(@RequestParam(value = "id", required = true) String id, Model model) {
		Long trueId = Long.parseLong(id);
		Computer computer = computerService.getById(trueId);
		ComputerDTO computerDTO = ComputerMapper.fromEntityToDto(computer);
		if (computer != null) {
			List<Company> companies = companyService.getAll();
			List<CompanyDTO> companyDTOs = new ArrayList<>();
			for (Company company : companies) {
				companyDTOs.add(CompanyMapper.FromEntityToDto(company));
			}
			model.addAttribute("companies", companyDTOs);
			model.addAttribute("computerDTO", computerDTO);
		}
		return "editComputer";
	}

	/**
	 * handle POST request. Validate data from the request and give them to the
	 * service layer to update a computer.
	 * 
	 */
	@RequestMapping(value = "/editcomputer", method = RequestMethod.POST)
	protected String updateComputer(@RequestParam(value = "id", required = true) String id,
			@Valid @ModelAttribute(value = "computerDTO") ComputerDTO computerDTO, BindingResult bindingResult,
			Model model) {
		System.out.println("test");
		if (!bindingResult.hasErrors()) {
			Computer toUpdate = ComputerMapper.fromDtoToEntity(computerDTO);
			computerService.update(toUpdate);
			System.out.println("error");
		} else {
			System.out.println("No error");
		}
		return displayEditableComputer(id, model);
	}
}
