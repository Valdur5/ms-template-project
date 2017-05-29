package org.wscale.mountains.service;

/*
 * Copyright 2017 Valentin Durst (www.wscale.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
    MountainEntity getMountain(long id);

    /**
     * Adds a mountain.
     * @param mountainEntity - The mountain we want to add.
     */
    MountainEntity addMountain(MountainEntity mountainEntity);
    
    /**
     * Updates a mountain.
     * @param mountainEntity - The object which should be replaced.
     */
    void updateMountain(long id, MountainEntity mountainEntity);

    /**
     * Patches partially a mountain.
     * @param id - The id of the mountain which should be patched.
     * @param mountainEntity - The object which should be replaced.
     */
    void patchMountain(long id, MountainEntity mountainEntity);

    /**
     * Deletes a mountain.
     * @param id - The id of the mountain which should be deleted.
     */
    void deleteMountain(long id);

}
