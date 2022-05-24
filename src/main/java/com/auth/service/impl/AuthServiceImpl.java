package com.auth.service.impl;


import com.auth.config.JwtTokenUtil;
import com.auth.domain.User;
import com.auth.dto.user.UserLoginDto;
import com.auth.enums.ResponseDescription;
import com.auth.enums.ResponseObject;
import com.auth.enums.ResponseStatus;
import com.auth.repository.RoleRepository;
import com.auth.repository.UserRepository;
import com.auth.service.AuthService;
import com.auth.utils.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

   @Autowired
   private PasswordEncoder bcryptEncoder;

    @Autowired
    PasswordValidationImpl passwordValidation;

    /**
     *
     * @param userLoginDto parameter to pass for token to be generated
     * @return return Authenticated Tokenization with time
     */
    public ResponseEntity<?> authenticateUser(UserLoginDto userLoginDto) {

       final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getUsername(),
                        userLoginDto.getPassword()
                )
        );
        User u = userRepository.findByUsername(userLoginDto.getUsername());

        SecurityContextHolder.getContext().setAuthentication(authentication);
         String token = jwtTokenUtil.generateToken((UserDetails) authentication);

        Map<Object, Object> jsonResponse = new HashMap();
        jsonResponse.put(Constants.TOKEN, token);
        jsonResponse.put(Constants.USER, u);
        return new ResponseObject().returnResponseBody(ResponseStatus.SUCCESS.getStatus(), ResponseDescription.AUTH_SUCCESS.getDescription(), jsonResponse);
    }

    /**
     *
     * @param user user information available for update
     * @return return the user value.
     */
    public ResponseEntity<?> updateUser(User user) {
        User dbuser = userRepository.findByEmail(user.getEmail());
        if (dbuser  == null){
            return new ResponseObject().returnResponseBody(ResponseStatus.ENTITY_NOT_FOUND.getStatus(), ResponseDescription.TOKEN_ENTITY_NOT_FOUND.getDescription());
        }

        if (user.getPassword() != null && user.getPassword().length() > 0){
            if (!user.getPassword().equals(user.getPasswordConfirm())){
                return new ResponseObject().returnResponseBody(ResponseStatus.GENERAL_ERROR.getStatus(), ResponseDescription.PASSWORD_NOT_MATCH.getDescription());
            }

            String pwdValid = passwordValidation.validatePassword(user.getPassword());
            if (pwdValid != null){
                return new ResponseObject().returnResponseBody(ResponseStatus.GENERAL_ERROR.getStatus(), pwdValid);
            }
            dbuser.setPassword(bcryptEncoder.encode(user.getPassword()));
            dbuser.setPasswordConfirm(bcryptEncoder.encode(user.getPasswordConfirm()));
        }
        userRepository.save(dbuser);

        return new ResponseObject().returnResponseBody(ResponseStatus.SUCCESS.getStatus(), ResponseDescription.AUTH_RESET_SUCCESS.getDescription());
    }



}
