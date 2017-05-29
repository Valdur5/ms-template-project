package org.wscale.mountains.mapper;

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

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wscale.commons.dto.ItemCollection;
import org.wscale.mountains.domain.MountainEntity;
import org.wscale.mountains.dto.Mountain;

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
