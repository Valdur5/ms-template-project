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

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * With the RestResponseEntityExceptionHandler we adding an Advice which tells Spring that all exceptions we are
 * registering here should be handled here. That brings the big advantage that we are now able to create a nice
 * result even there is an exception, that means that the client can use this well formatted result and work with it
 * much better than with the plain java exception.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { AbstractRuntimeException.class })
    protected ResponseEntity<Object> handleConflict(final AbstractRuntimeException ex, final WebRequest request) {
        return handleExceptionInternal(ex, ex.getErrorObjectAsJsonString(),
                new HttpHeaders(), ex.getStatus(), request);
    }

    /**
     * Creating a error message for the microservice user which tells him that you screwed it up. So better watch out ;)
     */
    @ExceptionHandler(value = { RuntimeException.class, Exception.class })
    protected ResponseEntity<Object> handleUnwrappedConflict(final Exception ex, final WebRequest request) {
        final String bodyOfResponse = "This should not happen and it is not your fault! Go talk to the microservice " +
                "provider and tell him that they should not use plain Runtime or Normal Exceptions. He should use " +
                "errors which extends the AbstractRuntimeException class. ";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
