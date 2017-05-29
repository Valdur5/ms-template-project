package org.wscale.commons.exceptions;

/*
 * Copyright 2017 Valentin Durst (www.wscale.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

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
        if (this.errorObject != null) {
            final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = "While creating the error message something went terribly wrong. Go talk to the microservice " +
                    "provider because he screwed it up.";
            try {
                json = ow.writeValueAsString(this.errorObject);
            } catch (final JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    protected void setErrorObject(final ErrorObject errorObject) {
        this.errorObject = errorObject;
    }

    HttpStatus getStatus() {
        return this.status;
    }

    protected void setStatus(final HttpStatus status) {
        this.status = status;
    }

}
