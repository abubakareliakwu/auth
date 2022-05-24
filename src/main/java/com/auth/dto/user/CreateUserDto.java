package com.auth.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CreateUserDto implements Serializable {

    private static final long serialVersionUID = -267291779096992505L;

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String passwordConfirm;
    private Long  roleId;


}
