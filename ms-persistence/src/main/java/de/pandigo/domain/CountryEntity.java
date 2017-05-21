package de.pandigo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int inhabitants;
    // Example for an attribute we don't want to expose to the public.
    private LocalTime dateAdded;

    public CountryEntity(String name, int inhabitants, LocalTime dateAdded) {
        this.name = name;
        this.inhabitants = inhabitants;
        this.dateAdded = dateAdded;
    }

    protected CountryEntity() {

    }

    public Long getId() {
        return id;
    }

    public LocalTime getDateAdded() {
        return dateAdded;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(int inhabitants) {
        this.inhabitants = inhabitants;
    }
}
