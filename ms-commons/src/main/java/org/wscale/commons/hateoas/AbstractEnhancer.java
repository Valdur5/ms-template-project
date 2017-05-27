package org.wscale.commons.hateoas;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

abstract class AbstractEnhancer<T extends ResourceSupport> {

    T enhanceWithActions(T obj, List<HateoasAction> actions) {
        for (final HateoasAction action : actions) {
            for (Object link : action.getLinks()) {
                obj.add(linkTo(link).withRel(action.getType()));
            }
        }
        return obj;
    }
}
