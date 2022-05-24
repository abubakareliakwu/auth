package com.auth.controller;

import com.auth.domain.Role;
import com.auth.domain.User;
import com.auth.repository.RoleRepository;
import com.auth.service.impl.RoleServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@CrossOrigin
@RequestMapping(value = "/role")
//@SecurityRequirement(name = "qapp")
@RequiredArgsConstructor
public class RoleController {

    @NonNull
    RoleServiceImpl roleService;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Role role) throws Exception {
        return roleService.save(role);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Role findById(@PathVariable Long id) throws EntityNotFoundException {
      //  return roleRepository.findById(id);

        return null;
    }

}
