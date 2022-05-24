package com.auth.service;

import com.auth.domain.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    ResponseEntity save(Role role);
}
