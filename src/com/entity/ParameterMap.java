package com.entity;

import java.io.Serializable;
import java.util.HashMap;

public class ParameterMap extends HashMap<String, Object> implements Serializable{
	 private   Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
