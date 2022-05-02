package com.ptc.carexercise.jwt;


import lombok.Data;

@Data
public class JwtRequestModel {

	private String username;

	private String password;
}
