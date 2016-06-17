package com.excilys.formation.controller;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.dto.CompanyDTO;
import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.entity.Company;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.util.CompanyMapper;
import com.excilys.formation.util.ComputerMapper;
import com.excilys.formation.util.ReturnInformation;
import com.excilys.formation.validator.ComputerDtoValidator;

/**
 * Servlet corresponding to the edit computer page.
 */
@WebServlet("/editcomputer")
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;

	public EditComputer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) {
		try {
			super.init(config);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		// SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
		// config.getServletContext());
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * Get request loading the page for a specific computer, and load the list
	 * of companies from database.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameterMap().containsKey("id")) {
			if (StringUtils.isNumeric(request.getParameter("id"))) {
				Long id = Long.parseLong(request.getParameter("id"));
				Computer computer = computerService.getById(id);
				ComputerDTO computerDTO = ComputerMapper.fromEntityToDto(computer);
				if (computer != null) {
					List<Company> companies = companyService.getAll();
					List<CompanyDTO> companyDTOs = new ArrayList<>();
					for (Company company : companies) {
						companyDTOs.add(CompanyMapper.FromEntityToDto(company));
					}
					request.setAttribute("companies", companyDTOs);
					request.setAttribute("computer", computerDTO);
					request.getRequestDispatcher("WEB-INF/editComputer.jsp").forward(request, response);
				}
			}
		}

	}

	/**
	 * handle POST request. Validate data from the request and give them to the
	 * service layer to update a computer.
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = null;
		ReturnInformation returnInformation = new ReturnInformation();
		if (request.getParameterMap().containsKey("computerName")
				&& request.getParameterMap().containsKey("computerId")) {
			String name = escapeHtml4(request.getParameter("computerName").trim());
			id = escapeHtml4(request.getParameter("computerId").trim());
			ComputerDTO computer = new ComputerDTO();
			computer.setName(name);
			computer.setId(id);
			if (request.getParameterMap().containsKey("introduced")) {
				computer.setIntroduced(escapeHtml4(request.getParameter("introduced").trim()));
			}
			if (request.getParameterMap().containsKey("discontinued")) {
				computer.setDiscontinued(escapeHtml4(request.getParameter("discontinued").trim()));
			}
			if (request.getParameterMap().containsKey("companyId")) {
				computer.setCompanyId(escapeHtml4(request.getParameter("companyId").trim()));
			}
			ComputerDtoValidator computerDtoValidator = new ComputerDtoValidator();
			returnInformation = computerDtoValidator.isValid(computer);
			if (returnInformation.isSuccess() == true) {
				request.setAttribute("computer", computer);
				Computer toUpdate = ComputerMapper.fromDtoToEntity(computer);
				System.out.println(toUpdate);
				computerService.update(toUpdate);
				returnInformation.addMessage("Successfully updated computer ");
				returnInformation.setSuccess(true);
			}
		} else {
			returnInformation.addMessage("Impossible to update computer without name");
			returnInformation.setSuccess(false);
		}
		request.setAttribute("successMessage", returnInformation.getMessages());
		request.setAttribute("success", returnInformation.isSuccess());
		request.setAttribute("display", true);
		if (id != null) {
			request.setAttribute("id", id);
		}
		List<Company> companies = companyService.getAll();
		List<CompanyDTO> companyDTOs = new ArrayList<>();
		for (Company company : companies) {
			companyDTOs.add(CompanyMapper.FromEntityToDto(company));
		}
		request.setAttribute("companies", companyDTOs);
		request.getRequestDispatcher("WEB-INF/editComputer.jsp").forward(request, response);
	}
}
