package org.wscale.mountains.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.pandigo.domain.MountainEntity;
import de.pandigo.repositories.MountainRepository;

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
        return this.mountainRepository.findOne(id);
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
        this.mountainRepository.delete(id);
    }

}
