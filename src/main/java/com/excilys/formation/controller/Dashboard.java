package com.excilys.formation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.dto.ComputerDTO;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.service.Page;
import com.excilys.formation.service.PageRequest;
import com.excilys.formation.util.RequestUtils;
import com.excilys.formation.util.ReturnInformation;

/**
 * Servlet corresponding to the dashboard page.
 */
@Controller
public class Dashboard {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(Dashboard.class);
	@Autowired
	private ComputerService computerService;

	@RequestMapping(value = "/dashboard")
	protected String doGet(ModelAndView model) {
		ReturnInformation returnInformation = new ReturnInformation();
		PageRequest pageRequest = new PageRequest();
		// pageRequest.extract(request, returnInformation);

		if (returnInformation.isSuccess()) {
			Page<ComputerDTO> page = computerService.getPage(pageRequest);
			LOG.debug(page.toString());
			model.addAttribute("page", page);
			model.setStatus(HttpStatus.I_AM_A_TEAPOT);
		}
		return "dashboard";
	}

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
