package com.ptc.carexercise.jwt;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String username) {
		if (username.equals("carexercise")) {
			return new User(
					"carexercise",
					"$2a$12$1qAMvG7coHlK1s1sNXU8eeCJZsp.OOzTAe9P7zVebsQJu5Vy7WkYm",
					new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}