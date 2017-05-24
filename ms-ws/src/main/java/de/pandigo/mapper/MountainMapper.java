package de.pandigo.mapper;

import java.util.ArrayList;
import java.util.List;

import de.pandigo.mountains.dto.ItemCollection;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.pandigo.domain.MountainEntity;
import de.pandigo.dto.Mountain;

//TODO commons?

@Component
public class MountainMapper extends DozerBeanMapper {

    @Autowired
    private DozerBeanMapper mapper;

    public Mountain mapEntityToDTO(final MountainEntity mountainEntity) {
        return this.mapper.map(mountainEntity, Mountain.class);
    }

    /**
     * Map the MountainEntry objects from the business layer to Mountain DTO objects with the help of Dozer framework.
     * @param mountainEntityList - The list of MountainEntities which whould be mapped.
     * @return - The result Mountains object.
     */
    public ItemCollection<Mountain> mapEntitiesToDTO(final List<MountainEntity> mountainEntityList) {
        final List<Mountain> mountainList = new ArrayList<>();
        for (final MountainEntity mountainEntity : mountainEntityList) {
            mountainList.add(mapEntityToDTO(mountainEntity));
        }
        return new ItemCollection<>(mountainList);
    }

    public MountainEntity mapDTOToEntity(final Mountain mountain) {
        return this.mapper.map(mountain, MountainEntity.class);
    }
}
