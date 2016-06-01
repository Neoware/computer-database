package com.excilys.formation.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.service.Page;
import com.excilys.formation.service.PageRequest;
import com.excilys.formation.util.RequestUtils;
import com.excilys.formation.util.ReturnInformation;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ComputerService computerService = ComputerService.getInstance();
	private static final Logger LOG = LoggerFactory.getLogger(Dashboard.class);

	/**
	 * Default constructor.
	 */
	public Dashboard() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
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
