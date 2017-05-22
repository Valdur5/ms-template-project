package de.pandigo.repositories;

import org.springframework.data.repository.CrudRepository;

import de.pandigo.domain.MountainEntity;

public interface MountainRepository extends CrudRepository<MountainEntity, Long> {

	/*
	 * Because for every objects it would very similar to that:
	 *
	 * getAllMountains()
	 * getMountain(Long id)
	 * updateMountain(Mountain m)
	 * deleteMountain(Long id)
	 *
	 * The Spring data JPA can guess that and creates the implementation for us.
	 */

	/**
	 * This method let us search for all mountains in a specific country. This can not be guessed
	 * from Spring Data JPA that is why we have to define the interface method here.
	 * 
	 * @param countryEntity - The country we want the mountains for.
	 * @return - All mountains in that specific country.
	 */
	//List<MountainEntity> getAllMountainsInCountry(CountryEntity countryEntity);
}
