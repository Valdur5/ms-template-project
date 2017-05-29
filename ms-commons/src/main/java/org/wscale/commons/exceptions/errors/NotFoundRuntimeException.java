package org.wscale.commons.exceptions.errors;

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
import org.wscale.commons.exceptions.AbstractRuntimeException;
import org.wscale.commons.exceptions.ErrorObject;

public class NotFoundRuntimeException extends AbstractRuntimeException {
    public NotFoundRuntimeException(final ErrorObject errorObject){
        if (errorObject != null) {
            errorObject.setTitle("Resource Not Found Exception");
            setErrorObject(errorObject);
        }
        setStatus(HttpStatus.NOT_FOUND);
    }
}
