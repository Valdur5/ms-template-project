package de.pandigo.domain;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private int inhabitants;
    // Example for an attribute we don't want to expose to the public.
    private LocalTime dateAdded;

    public CountryEntity(final Long id, final String name, final int inhabitants, final LocalTime dateAdded) {
        this.id = id;
        this.name = name;
        this.inhabitants = inhabitants;
        this.dateAdded = dateAdded;
    }

    protected CountryEntity() {

    }

    public Long getId() {
        return this.id;
    }

    public LocalTime getDateAdded() {
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
