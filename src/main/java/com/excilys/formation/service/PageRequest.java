package com.excilys.formation.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

public class PageRequest {
	private int page = 1;
	private int limit = 10;

	public PageRequest(HttpServletRequest request){
		if (request.getParameter("page") != null) {
			String pageString = escapeHtml4(request.getParameter("page").trim());
			if (pageString.length() > 0 && StringUtils.isNumeric(pageString))
			page = Integer.parseInt(pageString);
		}
		if (request.getParameter("limit") != null) {
			String limitString = escapeHtml4(request.getParameter("limit"));
			if (limitString.length() > 0 && StringUtils.isNumeric(limitString))
			limit = Integer.parseInt(limitString);
		}
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
}
