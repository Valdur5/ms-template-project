package org.wscale.mountains.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wscale.commons.test.AbstractIT;
import org.wscale.mountains.MsTemplateProjectApplication;
import org.wscale.mountains.domain.MountainEntity;
import org.wscale.mountains.repositories.MountainRepository;

import java.util.ArrayList;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = MsTemplateProjectApplication.class)
public class MountainServiceImplTest extends AbstractIT{

    @Autowired
    private MountainRepository mountainRepository;

    @Autowired
    private MountainService mountainService;

    @Test
    public void getAllMountains() throws Exception {
        when(this.mountainRepository.findAll()).thenReturn(new Iterable<MountainEntity>() {
            @Override
            public Iterator<MountainEntity> iterator() {
                return new ArrayList<MountainEntity>().iterator();
            }
        });
        assertThat(mountainService.getAllMountains().size(), is(0));
    }

    @Test
    public void getMountain() throws Exception {
    }

    @Test
    public void addMountain() throws Exception {
    }

    @Test
    public void updateMountain() throws Exception {
    }

    @Test
    public void patchMountain() throws Exception {
    }

    @Test
    public void deleteMountain() throws Exception {
    }

}