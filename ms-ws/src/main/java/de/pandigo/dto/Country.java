package de.pandigo.dto;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.hateoas.ResourceSupport;


@ApiObject
public class Country extends ResourceSupport {

    @ApiObjectField(description = "Name of the country.")
    private String name;
    @ApiObjectField(description = "The number of people living in that country.")
    private int inhabitants;

    public Country() {

    }

    public Country(String name, int inhabitants) {
        this.name = name;
        this.inhabitants = inhabitants;
    }

    public int getInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(int inhabitants) {
        this.inhabitants = inhabitants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
