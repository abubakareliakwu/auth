package com.auth.dto.userType;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CreateUserTypeDto implements Serializable {

    private static final long serialVersionUID = -267291779096992505L;

    @NotNull
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String description;


}
