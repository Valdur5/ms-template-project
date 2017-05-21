package de.pandigo.dto;

/**
 * DTO for a mountain
 */
public class Mountain{

    private String name;
    private int altitude;
    private String[] countries;
    private int firstAscent;
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
