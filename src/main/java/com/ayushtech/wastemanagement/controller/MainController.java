package com.ayushtech.wastemanagement.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ayushtech.wastemanagement.entities.User;
import com.ayushtech.wastemanagement.exception.EmailAlreadyRegisteredException;
import com.ayushtech.wastemanagement.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
public class MainController {
	
	private BCryptPasswordEncoder passwordEncoder;
	private UserRepository userRepository;

	public MainController(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@GetMapping("/test")
	public String test() {
		return "Hello World";
	}
	
	@PostMapping("/signup")
	public ResponseEntity<User> signup(@RequestBody @Valid User user) {
		Optional<User> tempOpt = userRepository.findByEmail(user.getEmail());
		if(tempOpt.isPresent()) {
			throw new EmailAlreadyRegisteredException("Email address already registered");
		}
		String password = user.getPassword();
		user.setPassword(passwordEncoder.encode(password));
		user.setAuthorities("ROLE_USER");
		User savedUser = userRepository.save(user);
		savedUser.setPassword(null);
		return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(savedUser);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(Principal principal) {
		String username = principal.getName();
		User  authorizedUser = userRepository.findByEmail(username).get();
		authorizedUser.setPassword(null);
		return ResponseEntity.of(Optional.of(authorizedUser));
	}
}
