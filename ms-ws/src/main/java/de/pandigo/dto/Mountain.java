package de.pandigo.dto;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * DTO for a mountain
 */
@ApiObject
public class Mountain{

    @ApiObjectField(description = "The name of the mountain.")
    private String name;
    @ApiObjectField(description = "The altitude of the mountain in meter.")
    private int altitude;
    @ApiObjectField(description = "List of countries where the mountain is located, some mountains have more than one country.")
    private String[] countries;
    @ApiObjectField(description = "Year of the first ascent.")
    private int firstAscent;
    @ApiObjectField(description = "List of names of the first ascenders.")
    private String[] firstAscenders;

    public Mountain() {

    }

    public Mountain(String name, int altitude, String[] countries) {
        this.name = name;
        this.altitude = altitude;
        this.countries = countries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public String[] getCountries() {
        return countries;
    }

    public void setCountries(String[] countries) {
        this.countries = countries;
    }

    public int getFirstAscent() {
        return firstAscent;
    }

    public void setFirstAscent(int firstAscent) {
        this.firstAscent = firstAscent;
    }

    public String[] getFirstAscenders() {
        return firstAscenders;
    }

    public void setFirstAscenders(String[] firstAscenders) {
        this.firstAscenders = firstAscenders;
    }
}
