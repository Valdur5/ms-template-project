package de.pandigo.dto;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class Countries extends ResourceSupport{

    private List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }

    public Countries(List<Country> countries) {
        this.countries = countries;
    }
}
