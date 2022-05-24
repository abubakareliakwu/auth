package com.auth.enums;

public enum ResponseStatus {
    // RESPONSE STATUS
    SUCCESS(200),
    SQL_ERROR(201),
    ENTITY_NOT_FOUND(202),
    ENTITY_ALREADY_EXISTS(203),
    AUTH_FAILED(204),
    ACCOUNT_BLOCKED(205),
    GENERAL_ERROR(206);


    private final int status;

    ResponseStatus(int status){
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}