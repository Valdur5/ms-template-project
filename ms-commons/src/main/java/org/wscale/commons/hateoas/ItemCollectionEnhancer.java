package org.wscale.commons.hateoas;

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

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.wscale.commons.dto.ItemCollection;

@Component
public class ItemCollectionEnhancer<T> extends AbstractEnhancer<ItemCollection<T>> {

    public ItemCollection<T> enhance(final ItemCollection<T> itemCollection, final Object selfAction, final List<HateoasAction> actions) {
        itemCollection.add(linkTo(selfAction).withSelfRel());
        return super.enhanceWithActions(itemCollection, actions);
    }

    public ItemCollection<T> enhance(final ItemCollection<T> itemCollection, final Object selfAction, final HateoasAction action) {
        itemCollection.add(linkTo(selfAction).withSelfRel());
        final List<HateoasAction> actions = new ArrayList<>();
        actions.add(action);
        return super.enhanceWithActions(itemCollection, actions);
    }

    public ItemCollection<T> enhance(final ItemCollection<T> itemCollection, final Object selfAction) {
        itemCollection.add(linkTo(selfAction).withSelfRel());
        return itemCollection;
    }
}
