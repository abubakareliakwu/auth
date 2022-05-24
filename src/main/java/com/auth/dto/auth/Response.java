package com.auth.dto.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

}
