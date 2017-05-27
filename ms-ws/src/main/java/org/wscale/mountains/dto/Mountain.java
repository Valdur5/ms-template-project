package org.wscale.mountains.dto;

import org.springframework.hateoas.ResourceSupport;

/**
 * DTO for a mountain
 */
public class Mountain extends ResourceSupport {

    private long mountainId;
    private String name;
    private int altitude;
    private int firstAscent;
    private String[] firstAscenders;

    public Mountain() {

    }

    public Mountain(long mountainId, String name, int altitude) {
        this.mountainId = mountainId;
        this.name = name;
        this.altitude = altitude;
    }

    public long getMountainId() {
        return mountainId;
    }

    public void setMountainId(long mountainId) {
        this.mountainId = mountainId;
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
