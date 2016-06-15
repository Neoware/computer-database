package com.excilys.formation.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.service.Page;
import com.excilys.formation.service.PageRequest;
import com.excilys.formation.util.RequestUtils;
import com.excilys.formation.util.ReturnInformation;

/**
 * Servlet corresponding to the dashboard page.
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(Dashboard.class);
	@Autowired
	private ComputerService computerService;

	/**
	 * Default constructor.
	 */
	public Dashboard() {
	}

	@Override
	public void init(ServletConfig config) {
		try {
			super.init(config);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	/**
	 * Method managing the display of the dashboard with possible url attributes
	 * that will be validated, and lead to a specific part of the computers that
	 * will be displayed.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReturnInformation returnInformation = new ReturnInformation();
		PageRequest pageRequest = new PageRequest();
		pageRequest.extract(request, returnInformation);
		if (returnInformation.isSuccess()) {
			Page<ComputerDTO> page = computerService.getPage(pageRequest);
			LOG.debug(page.toString());
			request.setAttribute("page", page);
			request.getRequestDispatcher("WEB-INF/dashboard.jsp").forward(request, response);
		}
	}

	/**
	 * This method manage the POST request allowing one to delete computers. It
	 * will get the data from the request and send it to the service layer.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameterMap().containsKey("selection")) {
			String selection = RequestUtils.getCleanParameter("selection", request);
			String[] toDeletes = selection.split(",");
			computerService.deleteList(toDeletes);
		}
		doGet(request, response);
	}
}
