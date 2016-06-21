package com.excilys.formation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.entity.Company;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.util.ComputerMapper;

/**
 * Servlet corresponding to the addComputer page
 */
@Controller
public class AddComputer {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;

	@RequestMapping(value = "/addcomputer", method = RequestMethod.GET)
	protected String displayDashboard(Model model) {
		List<Company> companies = companyService.getAll();
		model.addAttribute("companies", companies);
		model.addAttribute("computerDTO", new ComputerDTO());
		return "addComputer";
	}

	/**
	 * Method managing the POST request of adding a computer, verifying the data
	 * sent, mapping to an entity and calling the service. A ReturnInformation
	 * object is used to hold information about success, failure, and
	 * information of the process.
	 */

	@RequestMapping(value = "/addcomputer", method = RequestMethod.POST)
	protected String addNewComputer(@Valid @ModelAttribute(value = "computerDTO") ComputerDTO computerDTO,
			BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			Computer toAdd = ComputerMapper.fromDtoToEntity(computerDTO);
			computerService.create(toAdd);
		}
		List<Company> companies = companyService.getAll();
		model.addAttribute("companies", companies);
		return "addComputer";
	}
}
