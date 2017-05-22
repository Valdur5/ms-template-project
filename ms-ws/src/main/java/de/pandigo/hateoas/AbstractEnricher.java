package de.pandigo.hateoas;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public abstract class AbstractEnricher<T extends ResourceSupport> {

    public T enrich(T obj, List<HateoasAction> actions) {
        for (final HateoasAction action : actions) {
            for (Object link : action.getLinks()) {
                obj.add(linkTo(link).withRel(action.getType().toString()));
            }
        }
        return obj;
    }
}
