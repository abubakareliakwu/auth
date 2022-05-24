package com.auth.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponseDTO implements Serializable {

    private static final long serialVersionUID = 4782418920408694522L;

    private String code;

    private String description;

}
