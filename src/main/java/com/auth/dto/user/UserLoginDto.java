package com.auth.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserLoginDto implements Serializable {

    private static final long serialVersionUID = -267291779096992505L;


    @NotNull
    private String username;


    @NotNull
    public String password;

}
