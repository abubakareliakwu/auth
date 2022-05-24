package com.auth.service.impl;

import com.auth.domain.Role;
import com.auth.enums.ResponseDescription;
import com.auth.enums.ResponseObject;
import com.auth.enums.ResponseStatus;
import com.auth.repository.RoleRepository;
import com.auth.service.RoleService;
import com.auth.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseEntity save(Role role) {

        Map<Object, Object> jsonResponse = new HashMap();

        try {
            jsonResponse.put(Constants.ROLES, roleRepository.save(role));
            return new ResponseObject().returnResponseBody(ResponseStatus.SUCCESS.getStatus(), ResponseDescription.SUCCESS.getDescription(), jsonResponse);
        }catch(DataIntegrityViolationException exc){
            return new ResponseObject().returnResponseBody(ResponseStatus.SQL_ERROR.getStatus(), exc.getRootCause().getLocalizedMessage());
        }
    }
}
