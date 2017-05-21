package de.pandigo.domain;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
public class MountainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int altitude;
    @OneToMany
    private List<CountryEntity> countries;
    private int firstAscent;
    private String[] firstAscenders;
    // Example for an attribute we don't want to expose to the public.
    private LocalTime dateAdded;

    protected MountainEntity(){

    }

    public MountainEntity(String name, int altitude, List<CountryEntity> countries, int firstAscent, String[] firstAscenders, LocalTime dateAdded) {
        this.name = name;
        this.altitude = altitude;
        this.countries = countries;
        this.firstAscent = firstAscent;
        this.firstAscenders = firstAscenders;
        this.dateAdded = dateAdded;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAltitude() {
        return altitude;
    }

    public List<CountryEntity> getCountries() {
        return countries;
    }

    public int getFirstAscent() {
        return firstAscent;
    }

    public String[] getFirstAscenders() {
        return firstAscenders;
    }

    public LocalTime getDateAdded() {
        return dateAdded;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public void setCountries(List<CountryEntity> countries) {
        this.countries = countries;
    }

    public void setFirstAscent(int firstAscent) {
        this.firstAscent = firstAscent;
    }

    public void setFirstAscenders(String[] firstAscenders) {
        this.firstAscenders = firstAscenders;
    }
}
