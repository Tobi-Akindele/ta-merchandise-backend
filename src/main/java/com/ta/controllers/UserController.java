package com.ta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.dtos.Response;
import com.ta.dtos.UserDto;
import com.ta.exceptions.BadRequestException;
import com.ta.exceptions.NotFoundException;
import com.ta.models.User;
import com.ta.services.UserService;
import com.ta.utils.ConstantUtils;
import com.ta.utils.Utils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@PutMapping("/user/{userId}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @PathVariable String userId)
			throws NoSuchFieldException, SecurityException {

		if (Utils.isEmptyString(userId))
			throw new BadRequestException("400", "ID is required");

		if (userDto == null)
			throw new BadRequestException("400", "RequestBody is required");

		Utils.validateRequiredFields(userDto, ConstantUtils.UPDATABLE_USER_FIELDS);

		User user = userService.getById(userId);
		if (user == null)
			throw new NotFoundException("404", "User not found");

		user = userService.updateUser(user, userDto);

		return ResponseEntity.ok(new Response("User updated successfully", user));
	}
}