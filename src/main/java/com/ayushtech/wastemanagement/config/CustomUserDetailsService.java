package com.ayushtech.wastemanagement.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ayushtech.wastemanagement.entities.User;
import com.ayushtech.wastemanagement.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByEmail(username);
		if (optionalUser.isEmpty()) {
			throw new UsernameNotFoundException("Email ID is not registered");
		}
		User user = optionalUser.get();
		return new CustomUserDetails(user);
	}

}
