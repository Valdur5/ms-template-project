package org.wscale.commons.exceptions.errors;

import org.springframework.http.HttpStatus;
import org.wscale.commons.exceptions.AbstractRuntimeException;
import org.wscale.commons.exceptions.ErrorObject;

public class BadRequestRuntimeException extends AbstractRuntimeException{
    public BadRequestRuntimeException(ErrorObject errorObject){
        if (errorObject != null) {
            errorObject.setTitle("Bad Request Exception");
            setErrorObject(errorObject);
        }
        setStatus(HttpStatus.BAD_REQUEST);
    }
}
