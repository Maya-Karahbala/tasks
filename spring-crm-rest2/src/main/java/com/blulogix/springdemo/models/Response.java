package com.blulogix.springdemo.models;

import java.io.Serializable;

public class Response  implements Serializable {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private Object data;
	public Response() {
		// TODO Auto-generated constructor stub
	}
	public Response(Object data) {
		super();
		this.data = data;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
