package de.pandigo.hateoas;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import de.pandigo.controller.MountainController;
import de.pandigo.dto.Mountains;

@Component
public class MountainsEnricher implements Enricher<Mountains>{

   // TODO problem with multiple actions of the same type.
    @Override
    public Mountains enrich(final Mountains obj, final Map<String, Object> actions) {
        obj.add(linkTo(methodOn(MountainController.class).getAllMountains()).withSelfRel());
        final Set<String> keys = actions.keySet();
        for (final String key : keys) {
            final Object action = actions.get(key);
            obj.add(linkTo(action).withRel(key));
        }
        return obj;
    }

    @Override
    public Mountains enrich(final Mountains obj) {
        obj.add(linkTo(methodOn(MountainController.class).getAllMountains()).withSelfRel());
        return obj;
    }
}
