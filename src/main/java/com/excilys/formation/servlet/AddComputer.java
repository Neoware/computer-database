package com.excilys.formation.servlet;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.entity.Company;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.utils.ComputerMapper;
import com.excilys.formation.utils.ReturnInformation;
import com.excilys.formation.validator.ComputerDtoValidator;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/addcomputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CompanyService companyService = CompanyService.getInstance();
		List<Company> companies = companyService.getAll();
		request.setAttribute("companies", companies);
		request.getRequestDispatcher("WEB-INF/addComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ReturnInformation returnInformation = new ReturnInformation();
		if (request.getParameterMap().containsKey("computerName")
				&& request.getParameter("computerName").trim().length() != 0) {
			String name = escapeHtml4(request.getParameter("computerName").trim());
			ComputerDTO computer = new ComputerDTO();
			computer.setName(name);
			if (request.getParameterMap().containsKey("introduced")
					&& request.getParameter("introduced").trim().length() != 0) {
				computer.setIntroduced(escapeHtml4(request.getParameter("introduced").trim()));
			}
			if (request.getParameterMap().containsKey("discontinued")
					&& request.getParameter("discontinued").trim().length() != 0) {
				computer.setDiscontinued(escapeHtml4(request.getParameter("discontinued").trim()));
			}
			if (request.getParameterMap().containsKey("companyId")
					&& request.getParameter("companyId").trim().length() != 0
					&& StringUtils.isNumeric(request.getParameter("companyId").trim())) {

				computer.setCompanyId(Integer.parseInt(escapeHtml4(request.getParameter("companyId").trim())));
			}
			ComputerDtoValidator computerDtoValidator = new ComputerDtoValidator();
			returnInformation = computerDtoValidator.isValid(computer);
			if (returnInformation.isSuccess() == true) {
				Computer toAdd = ComputerMapper.FromDtoToEntity(computer);

				ComputerService computerService = ComputerService.getInstance();
				computerService.create(toAdd);
				returnInformation.getMessage().append("Successfully added computer " + computer.getName());
				returnInformation.setSuccess(true);
			}
		} else {
			returnInformation.getMessage().append("Impossible to add computer without name");
			returnInformation.setSuccess(false);
		}
		request.setAttribute("successMessage", returnInformation.getMessage().toString());
		request.setAttribute("success", returnInformation.isSuccess());
		request.setAttribute("display", true);
		doGet(request, response);
	}

}
