package de.pandigo.services;

import java.util.List;

import de.pandigo.domain.CountryEntity;

public interface CountryService {

    /**
     * Returns a list with all countries.
     * @return - List of Countries.
     */
    List<CountryEntity> getAllCountries();

    /**
     * Returns a specific country.
     * @param id - The id to specify the country.
     * @return - The country with the id.
     */
    CountryEntity getCountry(Long id);

    /**
     * The method to add a country.
     * @param countryEntity - The country we want to add.
     */
    CountryEntity addCountry(CountryEntity countryEntity);

    /**
     * The method to update the data for a country.
     * @param countryEntity - The data for the country we want to change.
     */
    void updatedCountry(CountryEntity countryEntity);

    /**
     * The method for deleting a country.
     * @param id - The id of the country we want to delete.
     */
    void deleteCountry(Long id);

    /**
     * This methods searches for the country with the most inhabitants.
     * @return - The country with the most inhabitants.
     */
    CountryEntity getCountryWithTheMostInhabitants();
}
