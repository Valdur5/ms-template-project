package org.wscale.mountains.service;

import java.util.List;

import org.wscale.mountains.domain.MountainEntity;

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

}
