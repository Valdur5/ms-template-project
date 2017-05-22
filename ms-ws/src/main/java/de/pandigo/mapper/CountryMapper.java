package de.pandigo.mapper;

import de.pandigo.domain.CountryEntity;
import de.pandigo.dto.Countries;
import de.pandigo.dto.Country;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CountryMapper extends DozerBeanMapper {

    @Autowired
    private DozerBeanMapper mapper;

    public Countries mapEntitiesToDTO(final List<CountryEntity> countryEntityList) {
        final List<Country> countryList = new ArrayList<>();
        for (final CountryEntity countryEntity : countryEntityList) {
            countryList.add(mapEntityToDTO(countryEntity));
        }
        return new Countries(countryList);
    }

    public Country mapEntityToDTO(final CountryEntity countryEntity) {
        return this.mapper.map(countryEntity, Country.class);
    }
}
