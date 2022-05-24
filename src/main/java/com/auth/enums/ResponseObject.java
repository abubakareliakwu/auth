package com.auth.enums;

import com.auth.domain.User;
import com.auth.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseObject {

    public final String PASSWORD = "*****";
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<?> returnResponseBody(Object code, String message) {
        Map<String, Object> jsonResponse = new HashMap();
        jsonResponse.put(Constants.STATUS, code);
        jsonResponse.put(Constants.MESSAGE, message);
        logger.info("RESPONSE===> " + jsonResponse.toString());
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
    }

    public ResponseEntity<?> returnResponseBody(Object code, String description, Map<Object, Object> jsonResponse) {
        if (jsonResponse.get(Constants.USER) != null){
            User user = (User) jsonResponse.get(Constants.USER);
            user.setPassword(PASSWORD);
            user.setPasswordConfirm(PASSWORD);
        }

        jsonResponse.put(Constants.STATUS, code);
        jsonResponse.put(Constants.MESSAGE, description);
        logger.info("RESPONSE===> " + jsonResponse.toString());
        return ResponseEntity.status(HttpStatus.OK).body(jsonResponse);
    }

}
