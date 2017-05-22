package de.pandigo.repositories;

import org.springframework.data.repository.CrudRepository;

import de.pandigo.domain.CountryEntity;

public interface CountryRepository extends CrudRepository<CountryEntity, Long>{

    /**
     * This method returns the country with the most inhabitants. This can not be guessed
     * from Spring Data JPA that is why we have to define the interface method here.
     * @return - The country with the most inhabitants.
     */
    //CountryEntity getCountryWithTheMostInhabitants();
}
