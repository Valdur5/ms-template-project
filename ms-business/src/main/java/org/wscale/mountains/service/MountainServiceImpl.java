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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wscale.commons.exceptions.ErrorObject;
import org.wscale.commons.exceptions.errors.BadRequestRuntimeException;
import org.wscale.commons.exceptions.errors.NotFoundRuntimeException;
import org.wscale.mountains.domain.MountainEntity;
import org.wscale.mountains.merger.MountainMerger;
import org.wscale.mountains.repositories.MountainRepository;

@Service
public class MountainServiceImpl implements MountainService{

    @Autowired
    private MountainRepository mountainRepository;

    @Autowired
    private MountainMerger mountainMerger;

    @Override
    public List<MountainEntity> getAllMountains() {
        final List<MountainEntity> mountains = new ArrayList<>();
        this.mountainRepository.findAll().forEach(mountains::add);
        return mountains;
    }

    @Override
    public MountainEntity getMountain(final long id) {
        final MountainEntity mountainEntity = this.mountainRepository.findOne(id);
        if (mountainEntity == null) {
            throw new NotFoundRuntimeException(
                    new ErrorObject("The mountain you have requested with the id "+id+" was not found.",
                            "Make sure you provide a existing mountain id here."));
        }
        return mountainEntity;
    }

    @Override
    public MountainEntity addMountain(final MountainEntity mountainEntity) {
        if (mountainEntity.getMountainId() != 0) {
            throw new BadRequestRuntimeException(
                    new ErrorObject("You are not permitted to define the mountainId in the create " +
                            "mountain operation because this could unexpected behaviour. Your created mountain will " +
                            "automatically get an mountainId assigned.",
                            "Remove the mountainId from your request body."));
        }
        return this.mountainRepository.save(mountainEntity);
    }

    @Override
    public void updateMountain(final long id, final MountainEntity mountainEntity) {
        // Make sure that there was no mountain id provided in the MountainEntity model.
        checkMountainIdForUpdate(mountainEntity.getMountainId());
        // Check if the mountain exists we are going to update.
        checkMountainExistsForUpdate(id);
        // Set the mountain id so that we can find the right mountain to update.
        mountainEntity.setMountainId(id);
        this.mountainRepository.save(mountainEntity);
    }

    @Override
    public void patchMountain(final long id, final MountainEntity mountainEntity) {
        // Make sure that there was no mountain id provided in the MountainEntity model.
        checkMountainIdForUpdate(mountainEntity.getMountainId());
        // Check if the mountain exists we are going to update.
        checkMountainExistsForUpdate(id);
        // Get mountain from mountain service.
        final MountainEntity loadedMountain = getMountain(id);
        // Merge the loaded mountain with the received mountain.
        final MountainEntity mergedMountain = this.mountainMerger.mergeMountains(loadedMountain, mountainEntity);
        this.mountainRepository.save(mergedMountain);
    }

    @Override
    public void deleteMountain(final long id) {
        final MountainEntity loadedMountain = this.mountainRepository.findOne(id);
        if (loadedMountain == null) {
            throw new NotFoundRuntimeException(
                    new ErrorObject("The mountain you want to delete with the id "+id+" does not exist.",
                            "Make sure you provide a existing mountain id here."));
        }
        this.mountainRepository.delete(id);
    }

    private void checkMountainExistsForUpdate(final long id) {
        if (this.mountainRepository.findOne(id) == null) {
            throw new NotFoundRuntimeException(new ErrorObject(
                    "The mountain you want to update does not exist.",
                    "Please provide a valid mountain id in the URL."));
        }
    }

    /**
     * This method checks if the mountain id is set in the request body for update operations. This is something we
     * don't want to have because the id is not changeable and can be different to the one in the url.
     * @param id - the id which is defined in the request body.
     */
    private void checkMountainIdForUpdate(final long id) {
        if (id != 0) {
            throw new BadRequestRuntimeException(
                    new ErrorObject("You are not permitted to define the mountainId in the update " +
                            "mountain operation because you don't need it to perform the update and if you wanted " +
                            "to change the id that is also not allowed.",
                            "Remove the mountainId from your request body."));
        }
    }

}
