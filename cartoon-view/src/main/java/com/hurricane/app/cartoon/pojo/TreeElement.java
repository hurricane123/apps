package com.hurricane.app.cartoon.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TreeElement {
	public static final String STATE_CLOSED = "closed";
	private String id;
	private String text;
	private String iconCls;
	private String state;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@JsonIgnore
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
