package com.ptc.carexercise.jwt;

import java.io.Serial;
import java.io.Serializable;

public class JwtResponseModel implements Serializable {

	@Serial
	private static final long serialVersionUID = -8091879091924046844L;
	private String jwtToken;
	private String type = "Bearer";

	public JwtResponseModel(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
