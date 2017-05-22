package de.pandigo.services;

import java.util.List;

import de.pandigo.domain.CountryEntity;
import de.pandigo.domain.MountainEntity;

public interface MountainService {

    /**
     * Returns a list of all the mountains in every country.
     * @return - All mountains.
     */
    List<MountainEntity> getAllMountains();

    /**
     * Returns a specific mountain with the given id.
     * @param id - The id of the mountain.
     * @return - The mountain object.
     */
    MountainEntity getMountain(Long id);

    /**
     * Adds a mountain.
     * @param mountainEntity - The mountain we want to add.
     */
    MountainEntity addMountain(MountainEntity mountainEntity);
    
    /**
     * Updates a mountain.
     * @param mountainEntity - The object which should be replaced.
     */
    void updateMountain(MountainEntity mountainEntity);

    /**
     * Deletes a mountain.
     * @param id - The id of the mountain which should be deleted.
     */
    void deleteMountain(Long id);

    /**
     * This methods returns a list of mountains which are located in the given country.
     * @param countryEntity - The country you want the mountains for.
     * @return - List of mountains.
     */
    List<MountainEntity> getAllMountainsInCountry(CountryEntity countryEntity);

}
