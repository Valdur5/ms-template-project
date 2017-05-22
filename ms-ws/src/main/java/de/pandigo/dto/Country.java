package de.pandigo.dto;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.hateoas.ResourceSupport;


@ApiObject
public class Country extends ResourceSupport {

    @ApiObjectField(description = "The Id of the mountain.")
    private long countryId;
    @ApiObjectField(description = "Name of the country.")
    private String name;
    @ApiObjectField(description = "The number of people living in that country.")
    private int inhabitants;

    public Country() {

    }

    public Country(long countryId, String name, int inhabitants) {
        this.countryId = countryId;
        this.name = name;
        this.inhabitants = inhabitants;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
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
