package de.pandigo.domain;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long countryId;

    private String name;
    private int inhabitants;
    // Example for an attribute we don't want to expose to the public.
    private LocalDate dateAdded;


    public CountryEntity(final String name, final int inhabitants, final LocalDate dateAdded) {
        this.name = name;
        this.inhabitants = inhabitants;
        this.dateAdded = dateAdded;
    }

    protected CountryEntity() {

    }

    public Long getCountryId() {
        return this.countryId;
    }

    public LocalDate getDateAdded() {
        return this.dateAdded;
    }

    public String getName() {

        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getInhabitants() {
        return this.inhabitants;
    }

    public void setInhabitants(final int inhabitants) {
        this.inhabitants = inhabitants;
    }
}
