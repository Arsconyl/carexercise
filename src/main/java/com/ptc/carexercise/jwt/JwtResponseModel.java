package com.ptc.carexercise.jwt;

import lombok.Data;


@Data
public class JwtResponseModel {

	private String jwtToken;

	private String type;

	public JwtResponseModel(String jwtToken) {
		this.jwtToken = jwtToken;
		this.type = "Bearer";
	}
}
