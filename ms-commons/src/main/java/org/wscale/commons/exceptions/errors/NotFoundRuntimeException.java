package org.wscale.commons.exceptions.errors;

import org.springframework.http.HttpStatus;
import org.wscale.commons.exceptions.AbstractRuntimeException;
import org.wscale.commons.exceptions.ErrorObject;

public class NotFoundRuntimeException extends AbstractRuntimeException {
    public NotFoundRuntimeException(ErrorObject errorObject){
        if (errorObject != null) {
            errorObject.setTitle("Resource Not Found Exception");
            setErrorObject(errorObject);
        }
        setStatus(HttpStatus.NOT_FOUND);
    }
}
