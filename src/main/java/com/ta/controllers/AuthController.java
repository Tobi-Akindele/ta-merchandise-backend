package com.ta.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.dtos.JwtResponse;
import com.ta.dtos.LoginRequest;
import com.ta.exceptions.BadRequestException;
import com.ta.exceptions.DuplicateException;
import com.ta.models.User;
import com.ta.services.UserService;
import com.ta.utils.Utils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		if(Utils.isEmptyString(loginRequest.getUsername()) 
				|| Utils.isEmptyString(loginRequest.getPassword()))
			throw new BadRequestException("400", "Username and Password is required");
		
		JwtResponse response = userService.login(loginRequest);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
		
		if(user == null)
			throw new BadRequestException("409", "RequestBody is required");
		
		if(userService.getByEmail(user.getEmail()) != null)
			throw new DuplicateException("409", "Email address already exists");
		
		if(userService.getByUsername(user.getUsername()) != null)
			throw new DuplicateException("409", "Username already exists");
		
		if (Utils.isEmptyString(user.getPassword()) || Utils.isEmptyString(user.getConfirmPassword()))
			throw new BadRequestException("400", "Password and Confirm password is required");

		if(!user.getPassword().equals(user.getConfirmPassword())) {
			throw new BadRequestException("400", "Password does not match");
		}
		
		user = userService.createUser(user);
		return ResponseEntity.ok(user);
	}
}
