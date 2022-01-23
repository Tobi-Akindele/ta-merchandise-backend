package com.ta.services.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ta.TestUtils.ModelUtils;
import com.ta.dao.UserRepository;
import com.ta.dtos.UserDto;
import com.ta.models.User;
import com.ta.security.jwt.JwtUtils;

public class IUserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    JwtUtils jwtUtils;
    @InjectMocks
    IUserService iUserService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetByUsernameSuccess() throws Exception {
    	Optional<User> user = Optional.of(ModelUtils.getUser());
        when(userRepository.findByUsername(anyString())).thenReturn(user);

        User result = iUserService.getByUsername("username");
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetByEmailSuccess() throws Exception {
    	Optional<User> user = Optional.of(ModelUtils.getUser());
        when(userRepository.findByEmail(anyString())).thenReturn(user);

        User result = iUserService.getByEmail("email");
        Assert.assertNotNull(result);
    }

    @Test
    public void testCreateUserSuccess() throws Exception {
    	User user = ModelUtils.getUser();
    	when(userRepository.save(any())).thenReturn(user);
    	
        User result = iUserService.createUser(user);
        Assert.assertEquals(user.getId(), result.getId());
    }

    @Test
    public void testUpdateUserSuccess() throws Exception {
    	User user = ModelUtils.getUser();
    	when(userRepository.save(any())).thenReturn(user);
    	
        User result = iUserService.updateUser(new User(), new UserDto());
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetByIdSuccess() throws Exception {
    	Optional<User> user = Optional.of(ModelUtils.getUser());
        when(userRepository.findById(anyString())).thenReturn(user);
    	
        User result = iUserService.getById("userId");
        Assert.assertNotNull(result);
    }

    @Test
    public void testDeleteUserSuccess() throws Exception {
    	doNothing().when(userRepository).delete(any());
        iUserService.deleteUser(new User());
        Assert.assertTrue(true);
    }

}