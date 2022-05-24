package com.auth.service.impl;

import com.auth.service.PasswordValidation;
import com.auth.utils.Constants;
import org.springframework.stereotype.Service;

@Service
public class PasswordValidationImpl implements PasswordValidation {

    public String validatePassword(String pwd){
        boolean lowerCase = false, upperCase = false, digit = false, character = false;

        if (pwd.length() < 8 )
            return Constants.PASSWORD_TOO_SHORT;
        if (pwd.length() > 30 )
            return Constants.PASSWORD_TOO_LONG;

        //convert String to char array
        char[] charArray = pwd.toCharArray();
        for(int i=0; i < charArray.length; i++){
            //if any character is not in upper case, return false
            if(Character.isUpperCase( charArray[i] ))
            {
                upperCase = true;
            }
        }
        if (!upperCase){
            return Constants.PASSWORD_UPPER_CASE;
        }

        for(int i=0; i < charArray.length; i++){
            //if any character is not in upper case, return false
            if(Character.isLowerCase( charArray[i] ))
            {
                lowerCase = true;
            }
        }
        if (!lowerCase){
            return Constants.PASSWORD_LOWER_CASE;
        }

        for(int i=0; i < charArray.length; i++){
            //if any character is not in upper case, return false
            if(Character.isDigit( charArray[i] ))
            {
                digit = true;
            }
        }
        if (!digit){
            return Constants.PASSWORD_DIGIT;
        }


        for(int i=0; i < charArray.length; i++){
            //if any character is not in upper case, return false
            if(Character.isAlphabetic( charArray[i] ))
            {
                character = true;
            }
        }
        if (!character){
            return Constants.PASSWORD_ALPHABETIC;
        }

        return null;
    }

}
