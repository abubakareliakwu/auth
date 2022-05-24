package com.auth.controller;


import com.auth.domain.Role;
import com.auth.domain.User;
import com.auth.dto.user.CreateUserDto;
import com.auth.dto.user.UserLoginDto;
import com.auth.repository.RoleRepository;
import com.auth.repository.UserRepository;
import com.auth.service.impl.AuthServiceImpl;
import com.auth.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
//@SecurityRequirement(name = "qapp")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserServiceImpl userDetailsService;

    @Autowired
    private AuthServiceImpl authService;

    @Resource
    UserRepository userRepository;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody CreateUserDto createUserDto) throws Exception {
        return userDetailsService.save(createUserDto);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto userLoginDto) throws Exception {
        return authService.authenticateUser(userLoginDto);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<?> validate(@RequestBody User user) throws Exception {
        return authService.updateUser(user);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User findById(@PathVariable String id) throws EntityNotFoundException {
        return userRepository.findById(Integer.valueOf(id)).get();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<User> findAll(final Pageable pageable) throws EntityNotFoundException {
        return userRepository.findAll(pageable);
    }
}