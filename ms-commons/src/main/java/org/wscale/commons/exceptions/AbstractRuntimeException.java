package org.wscale.commons.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpStatus;

/**
 * The AbstractRuntimeException is a base class for all the runtime exceptions we are going to write. It provides an
 * interface for all the child implementations to define a HTTPStatus.
 */
public abstract class AbstractRuntimeException extends RuntimeException{

    // Default error state is 500
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private ErrorObject errorObject;

    String getErrorObjectAsJsonString() {
        String json = "There was no data to describe the error. This is clearly not your fault! Go and blame the " +
                "microservice provider for creating meaningless errors!";
        if (errorObject != null) {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = "While creating the error message something went terribly wrong. Go talk to the microservice " +
                    "provider because he screwed it up.";
            try {
                json = ow.writeValueAsString(errorObject);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    protected void setErrorObject(ErrorObject errorObject) {
        this.errorObject = errorObject;
    }

    HttpStatus getStatus() {
        return status;
    }

    protected void setStatus(HttpStatus status) {
        this.status = status;
    }

}
