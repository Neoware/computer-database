package com.excilys.formation.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.entity.Computer;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.service.Page;
import com.excilys.formation.service.PageRequest;
import com.excilys.formation.util.ComputerMapper;

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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageRequest pageRequest = new PageRequest(request);
		ComputerService computerService = ComputerService.getInstance();
		List<Computer> computers = computerService.getPage(pageRequest);
		List<ComputerDTO> computerDTOs = new ArrayList<>();
		for (Computer computer : computers) {
			computerDTOs.add(ComputerMapper.FromEntityToDto(computer));
		}
		int count = computerService.count();
		Page<ComputerDTO> page = new Page(computerDTOs, pageRequest, count);
		int paginationStart = page.getCurrent() - 3;
		int paginationEnd = page.getCurrent() + 3;
		if (paginationStart < 1) {
			paginationStart = 1;
		}
		if (paginationEnd > page.getTotalPage()) {
			paginationEnd = page.getTotalPage();
		}
		request.setAttribute("computers", computers);
		request.setAttribute("page", page);
		request.setAttribute("paginationEnd", paginationEnd);
		request.setAttribute("paginationStart", paginationStart);
		request.getRequestDispatcher("WEB-INF/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
