package de.pandigo.dto;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class Countries extends ResourceSupport{

    private List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }

    public void addCountry(Country country) {
        this.countries.add(country);
    }

    public void setCountry(int index, Country country) {
        this.countries.set(index, country);
    }

    public void deleteCountry(int index) {
        this.countries.remove(index);
    }

    public Countries() {
        this.countries = new ArrayList<>();
    }
}
