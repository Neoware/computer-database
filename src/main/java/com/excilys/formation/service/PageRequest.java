package com.excilys.formation.service;

import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class PageRequest {
	private int page = 1;
	private int limit = 10;
	private int offset = 0;

	public PageRequest(HttpServletRequest request) {
		if (request.getParameter("page") != null) {
			String pageString = escapeHtml4(request.getParameter("page").trim());
			if (pageString.length() > 0 && StringUtils.isNumeric(pageString)) {
				page = Integer.parseInt(pageString);
			}
		}
		if (request.getParameter("limit") != null) {
			String limitString = escapeHtml4(request.getParameter("limit"));
			if (limitString.length() > 0 && StringUtils.isNumeric(limitString)) {
				limit = Integer.parseInt(limitString);
			}
		}
		offset = (page - 1) * limit;
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

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}
