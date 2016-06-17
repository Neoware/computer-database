package com.excilys.formation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excilys.formation.entity.Company;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;

/**
 * Servlet corresponding to the addComputer page
 */
@Controller
public class AddComputer {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;

	@RequestMapping(value = "/add")
	protected String displayDashboard(Model model) {
		List<Company> companies = companyService.getAll();
		model.addAttribute(companies);
		return "dashboard";
	}

	/**
	 * Method managing the POST request of adding a computer, verifying the data
	 * sent, mapping to an entity and calling the service. A ReturnInformation
	 * object is used to hold information about success, failure, and
	 * information of the process.
	 */
	/*
	 * @Override protected void doPost(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException {
	 * 
	 * ReturnInformation returnInformation = new ReturnInformation(); if
	 * (request.getParameterMap().containsKey("computerName")) { String name =
	 * escapeHtml4(request.getParameter("computerName").trim()); ComputerDTO
	 * computer = new ComputerDTO(); computer.setName(name); if
	 * (request.getParameterMap().containsKey("introduced")) {
	 * computer.setIntroduced(escapeHtml4(request.getParameter("introduced").
	 * trim())); } if (request.getParameterMap().containsKey("discontinued")) {
	 * computer.setDiscontinued(escapeHtml4(request.getParameter("discontinued")
	 * .trim())); } if (request.getParameterMap().containsKey("companyId")) {
	 * computer.setCompanyId(escapeHtml4(request.getParameter("companyId").trim(
	 * ))); } ComputerDtoValidator computerDtoValidator = new
	 * ComputerDtoValidator(); returnInformation =
	 * computerDtoValidator.isValid(computer); if (returnInformation.isSuccess()
	 * == true) { Computer toAdd = ComputerMapper.fromDtoToEntity(computer);
	 * computerService.create(toAdd); returnInformation.addMessage(
	 * "Successfully added computer "); returnInformation.setSuccess(true); } }
	 * else { returnInformation.addMessage(
	 * "Impossible to add computer without name");
	 * returnInformation.setSuccess(false); }
	 * request.setAttribute("successMessage", returnInformation.getMessages());
	 * request.setAttribute("success", returnInformation.isSuccess());
	 * request.setAttribute("display", true); doGet(request, response); }
	 */

}
