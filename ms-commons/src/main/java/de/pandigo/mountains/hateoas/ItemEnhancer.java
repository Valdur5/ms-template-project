package de.pandigo.mountains.hateoas;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class ItemEnhancer<T extends ResourceSupport> extends AbstractEnhancer<T> {

    public T enhance(T item, Object selfAction, final List<HateoasAction> actions) {
        item.add(linkTo(selfAction).withSelfRel());
        return super.enhanceWithActions(item, actions);
    }

    public T enhance(T item, Object selfAction, final HateoasAction action) {
        item.add(linkTo(selfAction).withSelfRel());
        List<HateoasAction> actions = new ArrayList<>();
        actions.add(action);
        return super.enhanceWithActions(item, actions);
    }

    public T enhance(T item, Object selfAction) {
        item.add(linkTo(selfAction).withSelfRel());
        return item;
    }
}