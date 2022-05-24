package com.auth.service.impl;

import com.auth.domain.Role;
import com.auth.domain.User;
import com.auth.dto.user.CompanyDto;
import com.auth.dto.user.CreateUserDto;
import com.auth.enums.ResponseDescription;
import com.auth.enums.ResponseObject;
import com.auth.enums.ResponseStatus;
import com.auth.repository.RoleRepository;
import com.auth.repository.UserRepository;
import com.auth.service.UserService;
import com.auth.utils.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
	protected final Log logger = LogFactory.getLog(this.getClass());

	@Resource
	private UserRepository userRepository;

	@Resource
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	PasswordValidationImpl passwordValidation;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword() ,getAuthority(user));
	}


	public Set getAuthority(User user) {
		Set authorities = new HashSet<>();
		/**user.getAccessByAccessId().getRolesById().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});*/
		return authorities;
	}

	@Override
	public ResponseEntity save(CreateUserDto createUserDto) {
		User user = new User();
		Map<Object, Object> jsonResponse = new HashMap();
		if (createUserDto.getPassword() != null && createUserDto.getPassword().length() > 0){
			if (!createUserDto.getPassword().equals(createUserDto.getPasswordConfirm())){
				return new ResponseObject().returnResponseBody(ResponseStatus.GENERAL_ERROR.getStatus(), ResponseDescription.PASSWORD_NOT_MATCH.getDescription(), jsonResponse);
			}

			String pwdValid = passwordValidation.validatePassword(createUserDto.getPassword());
			if (pwdValid != null){
				return new ResponseObject().returnResponseBody(ResponseStatus.GENERAL_ERROR.getStatus(), pwdValid, jsonResponse);
			}
			Role role = roleRepository.findById(createUserDto.getRoleId()).orElseThrow(()
					-> new EntityNotFoundException("Stores Not Found."));
			user.setPassword(bcryptEncoder.encode(createUserDto.getPassword()));
			user.setPasswordConfirm(bcryptEncoder.encode(createUserDto.getPasswordConfirm()));
			user.setEmail(createUserDto.getEmail());
			user.setLastname(createUserDto.getLastname());
			user.setFirstname(createUserDto.getFirstname());
			user.setUsername(createUserDto.getUsername());
			user.setRoleId(role);
		}

		try {
			jsonResponse.put(Constants.USER, userRepository.save(user));
			return new ResponseObject().returnResponseBody(ResponseStatus.SUCCESS.getStatus(), ResponseDescription.USER_REGISTRATION.getDescription(), jsonResponse);
		}catch(DataIntegrityViolationException exc){
			return new ResponseObject().returnResponseBody(ResponseStatus.SQL_ERROR.getStatus(), exc.getRootCause().getLocalizedMessage(), jsonResponse);
		}catch (ConstraintViolationException exc){
			return new ResponseObject().returnResponseBody(ResponseStatus.SQL_ERROR.getStatus(), exc.getCause().getLocalizedMessage(), jsonResponse);
		}
	}

}