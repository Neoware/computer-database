package com.excilys.formation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.service.Page;
import com.excilys.formation.service.PageRequest;
import com.excilys.formation.util.ReturnInformation;

/**
 * Servlet corresponding to the dashboard page.
 */
@Controller
public class Dashboard {
	private static final Logger LOG = LoggerFactory.getLogger(Dashboard.class);
	@Autowired
	private ComputerService computerService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	protected String displayTab(@ModelAttribute("pageRequest") PageRequest pageRequest, Model model) {
		ReturnInformation returnInformation = new ReturnInformation();
		pageRequest.init();
		if (returnInformation.isSuccess()) {
			Page<ComputerDTO> page = computerService.getPage(pageRequest);
			model.addAttribute("page", page);
		}
		return "dashboard";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.POST)
	protected void deleteComputers(@ModelAttribute("selection") String selection, Model model) {
		String[] toDeletes = selection.split(",");
		computerService.deleteList(toDeletes);
		displayTab(new PageRequest(), model);
	}
}
