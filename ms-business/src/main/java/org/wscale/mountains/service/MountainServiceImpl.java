package org.wscale.mountains.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.wscale.commons.exceptions.ErrorObject;
import org.wscale.commons.exceptions.errors.NotFoundRuntimeException;
import org.wscale.mountains.domain.MountainEntity;
import org.wscale.mountains.repositories.MountainRepository;

@Service
public class MountainServiceImpl implements MountainService{

    @Autowired
    private MountainRepository mountainRepository;

    @Override
    public List<MountainEntity> getAllMountains() {
        final List<MountainEntity> mountains = new ArrayList<>();
        this.mountainRepository.findAll().forEach(mountains::add);
        return mountains;
    }

    @Override
    public MountainEntity getMountain(final Long id) {
        MountainEntity mountainEntity = this.mountainRepository.findOne(id);
        if (mountainEntity == null) {
            throw new NotFoundRuntimeException(
                    new ErrorObject("The mountain you have requested with the id "+id+" was not found.",
                            "Make sure you provide a existing mountain id here."));
        }
        return mountainEntity;
    }

    @Override
    public MountainEntity addMountain(final MountainEntity mountainEntity) {
        return this.mountainRepository.save(mountainEntity);
    }

    @Override
    public void updateMountain(final MountainEntity mountainEntity) {
        this.mountainRepository.save(mountainEntity);
    }

    @Override
    public void deleteMountain(final Long id) {
        MountainEntity loadedMountain = this.mountainRepository.findOne(id);
        if (loadedMountain == null) {
            throw new NotFoundRuntimeException(
                    new ErrorObject("The mountain you want to delete with the id "+id+" does not exist.",
                            "Make sure you provide a existing mountain id here."));
        }
        this.mountainRepository.delete(id);
    }

}
