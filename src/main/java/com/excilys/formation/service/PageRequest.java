package com.excilys.formation.service;

import javax.servlet.http.HttpServletRequest;

import com.excilys.formation.util.RequestUtils;
import com.excilys.formation.util.ReturnInformation;
import com.excilys.formation.validator.PageRequestValidator;

public class PageRequest {
	private Integer page = 1;
	private Integer limit = 10;
	private String search;
	private String sort;
	private Integer offset;
	private String order;

	public PageRequest() {
	}

	public void extract(HttpServletRequest request, ReturnInformation returnInformation) {
		PageRequestValidator validator = new PageRequestValidator(returnInformation);
		String pageString = RequestUtils.getCleanParameter("page", request);
		String limitString = RequestUtils.getCleanParameter("limit", request);
		if (validator.validatePage(pageString) == true) {
			if (pageString == null || pageString.isEmpty()) {
				this.page = 1;
			} else {
				this.page = Integer.parseInt(pageString);
			}
		}
		if (validator.validateLimit(limitString) == true) {
			if (limitString == null || limitString.isEmpty()) {
				this.limit = 10;
			} else {
				this.limit = Integer.parseInt(limitString);
			}
		}
		if (limit != null && page != null) {
			this.offset = (page - 1) * limit;
		}
		this.search = RequestUtils.getCleanParameter("search", request);
		this.sort = RequestUtils.getCleanParameter("sort", request);
		this.order = RequestUtils.getCleanParameter("order", request);
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}


	
}
