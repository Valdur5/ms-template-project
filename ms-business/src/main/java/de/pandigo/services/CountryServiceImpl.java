package de.pandigo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.pandigo.domain.CountryEntity;
import de.pandigo.repositories.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService{

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<CountryEntity> getAllCountries() {
        final List<CountryEntity> countryEntities = new ArrayList<>();
        this.countryRepository.findAll().forEach(countryEntities::add);
        return countryEntities;
    }

    @Override
    public CountryEntity getCountry(final Long id) {
        return this.countryRepository.findOne(id);
    }

    @Override
    public CountryEntity addCountry(final CountryEntity countryEntity) {
        return this.countryRepository.save(countryEntity);
    }

    @Override
    public void updatedCountry(final CountryEntity countryEntity) {
        this.countryRepository.save(countryEntity);
    }

    @Override
    public void deleteCountry(final Long id) {
        this.countryRepository.delete(id);
    }

    @Override
    public CountryEntity getCountryWithTheMostInhabitants() {
        return null;
    }
}
