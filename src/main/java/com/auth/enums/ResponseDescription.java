package com.auth.enums;

public enum ResponseDescription {

    // AUTHORISATION
    AUTH_FAILED("Authorisation failed."),
    AUTH_FAILED_ACTIVE("Authorisation failed. Account not active"),
    AUTH_LOGIN_FAILED_ACTIVE("login failed. Kindly check your email and password"),
    AUTH_SUCCESS("Authorisation granted."),
    AUTH_RESET_SUCCESS("Your password has been reset successfully. You may proceed to login."),


    SUCCESS("Success"),
    MISSING_PARAMETERS("Missing parameters."),


    ENTITY_NOT_FOUND("Entity does not exist."),

    EMAIL_ENTITY_NOT_FOUND("The email you have entered is not registered on this system, please retry or proceed to register."),
    TOKEN_ENTITY_NOT_FOUND("The token you have entered is not registered on this system, please retry or proceed to register."),

    ENTITY_ALREADY_EXISTS("Entity already exists."),
    ACCOUNT_BLOCKED("Too many login tries. Account locked."),
    GENERAL_ERROR("General Error."),
    PASSWORD_NOT_MATCH("The passwords you entered do not match."),
    LICENCE("licence"),
    USER_REGISTRATION ("You have successfully registered your account, you many proceed to login."),
    PASSWORD_RESET ("An email has been sent to you, please follow the instructions to reset your password.");
    private final String description;

    ResponseDescription(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
