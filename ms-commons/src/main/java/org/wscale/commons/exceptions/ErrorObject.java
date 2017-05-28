package org.wscale.commons.exceptions;

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

    public ErrorObject(String description, String solution) {
        this.description = description;
        this.solution = solution;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
