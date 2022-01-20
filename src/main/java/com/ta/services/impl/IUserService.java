/**
 * 
 */
package com.ta.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ta.dao.UserRepository;
import com.ta.dtos.JwtResponse;
import com.ta.dtos.LoginRequest;
import com.ta.dtos.UserDto;
import com.ta.dtos.Stats;
import com.ta.enums.Roles;
import com.ta.models.User;
import com.ta.security.jwt.JwtUtils;
import com.ta.security.services.UserDetailsImpl;
import com.ta.services.UserService;
import com.ta.utils.BeanUtils;
import com.ta.utils.ConstantUtils;

/**
 * @author oyindamolaakindele
 *
 */

@Service
public class IUserService implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public User getByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

	@Override
	public User getByEmail(String email) {
		return userRepository.findByEmail(email).orElse(null);
	}

	@Override
	public User createUser(User user) {
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Set<String> roles = new HashSet<>();
		roles.add(user.isAdmin() ? Roles.ROLE_ADMIN.name() : Roles.ROLE_USER.name());
		user.setRoles(roles);

		return userRepository.save(user);
	}

	@Override
	public JwtResponse login(LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
				userDetails.isAdmin(), roles);
	}

	@Override
	public User updateUser(User user, UserDto userDto) throws NoSuchFieldException, SecurityException {
		BeanUtils<User, UserDto> beanUtils = new BeanUtils<>();
		beanUtils.copyUpdatableFields(user, userDto, ConstantUtils.UPDATABLE_USER_FIELDS);
		user.setUpdatedAt(new Date());
		return userRepository.save(user);
	}

	@Override
	public User getById(String userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public List<User> getByLimit(boolean limit) {
		final Pageable pageRequest = PageRequest.of(0, 5, Sort.by(Direction.DESC, "createdAt"));
		return limit ? userRepository.findAll(pageRequest).getContent() : userRepository.findAll();
	}

	@Override
	public List<Stats> getUserStats() {
		LocalDateTime ldt = LocalDateTime.now().minusYears(1);
		Date lastYear = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		return userRepository.getUserStats(lastYear);
	}
}
