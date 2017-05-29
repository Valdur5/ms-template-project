package org.wscale.mountains.hateoas;

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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.stereotype.Component;
import org.wscale.commons.hateoas.HateoasAction;
import org.wscale.commons.hateoas.ItemEnhancer;
import org.wscale.mountains.controller.MountainController;
import org.wscale.mountains.dto.Mountain;

// TODO commons?

@Component
public class MountainNavigationEnhancer extends ItemEnhancer<Mountain> {

    public Mountain enhanceWithNavigation(final Mountain mountain, final List<Mountain> allMountains, final Object selfAction) {
        if (allMountains != null) {
            final int index = allMountains.indexOf(getMountainOfList(allMountains, mountain));
            if (index > 0) {
                mountain.add(linkTo(methodOn(MountainController.class)
                        .getMountain(allMountains.get(index-1).getMountainId())).withRel(ActionType.prev.toString()));
            }
            if (index < allMountains.size()-1) {
                mountain.add(linkTo(methodOn(MountainController.class)
                        .getMountain(allMountains.get(index+1).getMountainId())).withRel(ActionType.next.toString()));
            }
        }
        return super.enhance(mountain, selfAction,
                new HateoasAction(ActionType.showall.toString(), methodOn(MountainController.class).getAllMountains()));
    }

    private Mountain getMountainOfList(final List<Mountain> mountains, final Mountain mountain) {
        for (final Mountain m : mountains) {
            if (m.getMountainId() == mountain.getMountainId()) {
                return m;
            }
        }
        return mountain;
    }

}
