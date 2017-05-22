package de.pandigo.mapper;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import de.pandigo.controller.MountainController;
import de.pandigo.domain.MountainEntity;
import de.pandigo.dto.Mountain;
import de.pandigo.dto.Mountains;

@Component
public class MountainMapper extends DozerBeanMapper {

    private final DozerBeanMapper mapper = new DozerBeanMapper();

    public Mountain mapEntityToDTO(final MountainEntity mountainEntity) {
        final Mountain mountain = this.mapper.map(mountainEntity, Mountain.class);
        mountain.add(linkTo(methodOn(MountainController.class).getMountain(mountainEntity.getId())).withSelfRel());
        return mountain;
    }

    /**
     * Map the MountainEntry objects from the business layer to Mountain DTO objects with the help of Dozer framework.
     * @param mountainEntityList - The list of MountainEntities which whould be mapped.
     * @return - The result Mountains object.
     */
    public Mountains mapEntitiesToDTO(final List<MountainEntity> mountainEntityList) {
        final List<Mountain> mountainList = new ArrayList<>();
        for (final MountainEntity mountainEntity : mountainEntityList) {
            mountainList.add(mapEntityToDTO(mountainEntity));
        }
        return new Mountains(mountainList);
    }

    public MountainEntity mapDTOToEntity(final Mountain mountain) {
        return this.mapper.map(mountain, MountainEntity.class);
    }
}
