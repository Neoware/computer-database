package com.excilys.formation.servlet;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.entity.Company;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.util.ComputerMapper;
import com.excilys.formation.util.ReturnInformation;
import com.excilys.formation.validator.ComputerDtoValidator;

/**
 * Servlet corresponding to the addComputer page
 */
@WebServlet("/addcomputer")
public class AddComputer extends HttpServlet {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * doGet method loading companies from service in order to display them in
	 * the htl select to allow the user to choose from existing companies and
	 * sending the addComputer JSP.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Company> companies = companyService.getAll();
		request.setAttribute("companies", companies);
		request.getRequestDispatcher("WEB-INF/addComputer.jsp").forward(request, response);
	}

	/**
	 * Method managing the POST request of adding a computer, verifying the data
	 * sent, mapping to an entity and calling the service. A ReturnInformation
	 * object is used to hold information about success, failure, and
	 * information of the process.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ReturnInformation returnInformation = new ReturnInformation();
		if (request.getParameterMap().containsKey("computerName")) {
			String name = escapeHtml4(request.getParameter("computerName").trim());
			ComputerDTO computer = new ComputerDTO();
			computer.setName(name);
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
				Computer toAdd = ComputerMapper.fromDtoToEntity(computer);
				computerService.create(toAdd);
				returnInformation.addMessage("Successfully added computer ");
				returnInformation.setSuccess(true);
			}
		} else {
			returnInformation.addMessage("Impossible to add computer without name");
			returnInformation.setSuccess(false);
		}
		request.setAttribute("successMessage", returnInformation.getMessages());
		request.setAttribute("success", returnInformation.isSuccess());
		request.setAttribute("display", true);
		doGet(request, response);
	}

}
