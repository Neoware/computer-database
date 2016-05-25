package com.excilys.formation.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.command.Page;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.service.ComputerService;
import com.mysql.jdbc.log.Log;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Dashboard() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageRequest = 1;
		int limit = 10;
		if (request.getParameterMap().containsKey("page")) {
			pageRequest = Integer.parseInt(request.getParameter("page"));
		}
		if (request.getParameterMap().containsKey("limit")) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		ComputerService computerService = ComputerService.getInstance();
		List<Computer> computers = new ArrayList<>();
		Page<Computer> pages = new Page<Computer>(computerService, 10);
		int pageCount = pages.getTotalPage();
		int numberElements = pages.getNumberElements();
		computers = pages.getPageElements(pageRequest);
		request.setAttribute("computers", computers);
		request.setAttribute("page", pageRequest);
		request.setAttribute("count", pageCount);
		request.setAttribute("numberElements", numberElements);
		request.setAttribute("limit", limit);
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
