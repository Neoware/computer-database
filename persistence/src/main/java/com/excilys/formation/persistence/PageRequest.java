package com.excilys.formation.persistence;

/**
 * Object extracting data from the servlet request and holding all its
 * information for further treatment.
 * 
 * @author neoware
 *
 */
public class PageRequest {
	private Integer page = 1;
	private Integer limit = 10;
	private String search;
	private String sort;
	private Integer offset = 0;
	private String order;

	public PageRequest() {
	}

	/**
	 * Extract data from servlet request.
	 * 
	 * @param request
	 *            the request from which informations will be extracted.
	 * @param returnInformation
	 *            Object containing information about failure or success of the
	 *            process and information about it.
	 */
	public void init() {
		if (limit != null && page != null) {
			this.offset = (page - 1) * limit;
		}
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

	@Override
	public String toString() {
		return "PageRequest [page=" + page + ", limit=" + limit + ", search=" + search + ", sort=" + sort + ", offset="
				+ offset + ", order=" + order + "]";
	}
}
