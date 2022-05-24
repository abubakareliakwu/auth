package com.auth.utils;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessValidationException extends RuntimeException {

    private static final long serialVersionUID = -1429946199216158662L;

    public BusinessValidationException(final String s) {
        super(s);
    }

   /** public BusinessValidationException(final Response response) {
        this(response.body().toString());
    }*/
}
