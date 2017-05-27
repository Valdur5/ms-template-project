package de.pandigo.commons.hateoas;

import de.pandigo.commons.dto.ItemCollection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class ItemCollectionEnhancer<T> extends AbstractEnhancer<ItemCollection<T>> {

    public ItemCollection<T> enhance(ItemCollection<T> itemCollection, Object selfAction, final List<HateoasAction> actions) {
        itemCollection.add(linkTo(selfAction).withSelfRel());
        return super.enhanceWithActions(itemCollection, actions);
    }

    public ItemCollection<T> enhance(ItemCollection<T> itemCollection, Object selfAction, final HateoasAction action) {
        itemCollection.add(linkTo(selfAction).withSelfRel());
        List<HateoasAction> actions = new ArrayList<>();
        actions.add(action);
        return super.enhanceWithActions(itemCollection, actions);
    }

    public ItemCollection<T> enhance(ItemCollection<T> itemCollection, Object selfAction) {
        itemCollection.add(linkTo(selfAction).withSelfRel());
        return itemCollection;
    }
}
