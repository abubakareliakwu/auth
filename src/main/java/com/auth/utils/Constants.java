package com.auth.utils;

public class Constants {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS  = 5*60*60;
    public static final String SIGNING_KEY                  = "devglan123r";
    public static final String AUTHORITIES_KEY              = "authorities";

    // RESPONSE MESSAGE GENERIC TYPES
    public static final String STATUS                       = "status";
    public static final String MESSAGE                      = "message";
    public static final String TOKEN                        = "token";

    // ENTITY OBJECT MAPPINGS
    public static final String USER                         = "user";
    public static final String PLATFORM                     = "platform";
    public static final String USER_TYPE                    = "userType";
    public static final String ROLES                       = "roles";
    public static final int PLATFORM_ID                     = 1;

    // PASSWORD VALIDATION
    public static final String PASSWORD_TOO_SHORT           = "Password must contain at least 8 characters.";
    public static final String PASSWORD_TOO_LONG            = "Password must contain at most 30 characters";
    public static final String PASSWORD_UPPER_CASE          = "Password must contain at least 1 upper case.";
    public static final String PASSWORD_LOWER_CASE          = "Password must contain at least 1 lower case.";
    public static final String PASSWORD_DIGIT               = "Password must contain at least 1 digit.";
    public static final String PASSWORD_ALPHABETIC          = "Password must contain at least 1 alphabetic character.";
}
