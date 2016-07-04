package com.excilys.formation.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.excilys.formation.mapper.ComputerMapper;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;

/**
 * Controller corresponding to the addComputer page
 */
@Controller
public class AddComputer {
	private static final Logger LOG = LoggerFactory.getLogger(AddComputer.class);
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;

	/**
	 * Method managing the GET request, displaying the add computer page.
	 * 
	 * @param model
	 *            Hold the company list and an empty computerDTO for the view.
	 * @return the addComputer view.
	 */
	@RequestMapping(value = "/addcomputer", method = RequestMethod.GET)
	protected String displayAddComputerPage(Model model) {
		LOG.debug("Requesting GET on addcomputer page");
		List<Company> companies = companyService.getAll();
		model.addAttribute("companies", companies);
		model.addAttribute("computerDTO", new ComputerDTO());
		return "addComputer";
	}

	/**
	 * 
	 * Method managing the POST request of adding a computer, verifying the data
	 * sent, mapping to an entity and calling the service.
	 * 
	 * @param computerDTO
	 *            the computer that will be added.
	 * @param bindingResult
	 *            The object containing eventual errors.
	 * @param model
	 *            Holding the company list and the eventual success message.
	 * @return the addComputer view.
	 */
	@RequestMapping(value = "/addcomputer", method = RequestMethod.POST)
	protected String addNewComputer(@Valid @ModelAttribute(value = "computerDTO") ComputerDTO computerDTO,
			BindingResult bindingResult, Model model) {
		LOG.debug("Requesting POST on addcomputer page with " + computerDTO.toString());
		if (!bindingResult.hasErrors()) {
			LOG.debug("Computer to add is valid");
			Computer toAdd = ComputerMapper.fromDtoToEntity(computerDTO);
			computerService.create(toAdd);
			model.addAttribute("successMessage", "Computer successfully added");
		} else {
			LOG.debug("Computer to add get errors");
		}
		List<Company> companies = companyService.getAll();
		model.addAttribute("companies", companies);
		return "addComputer";
	}
}
