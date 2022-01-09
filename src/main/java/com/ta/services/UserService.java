package com.ta.services;

import java.util.List;

import com.ta.dtos.JwtResponse;
import com.ta.dtos.LoginRequest;
import com.ta.dtos.UserDto;
import com.ta.models.User;

public interface UserService {

	public User getByUsername(String username);
	
	public User getByEmail(String email);
	
	public User createUser(User user);
	
	public JwtResponse login(LoginRequest loginRequest);
	
	public User updateUser(User user, UserDto userDto) throws NoSuchFieldException, SecurityException;

	public User getById(String userId);
	
	public void deleteUser(User user);
	
	public List<User> getByLimit(boolean limit);
}
