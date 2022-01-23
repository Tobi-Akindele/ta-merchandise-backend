package com.ta.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {

	@JsonProperty("_id")
	private Long id;
	private Long total;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
}
