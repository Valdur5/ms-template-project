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

public class ErrorObject {

    /**
     * The title needs to be set from the implementation of the specific exception type.
     */
    private String title;
    /**
     * The description field should describe what went wrong without providing a solution.
     */
    private String description;
    /**
     * The solution should be filled so that the user of our microservice can figure out how to solve the problem.
     */
    private String solution;

    public ErrorObject(final String description, final String solution) {
        this.description = description;
        this.solution = solution;
    }

    public String getSolution() {
        return this.solution;
    }

    public void setSolution(final String solution) {
        this.solution = solution;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }


}
