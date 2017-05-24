package de.pandigo.hateoas;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import de.pandigo.mountains.hateoas.AbstractEnricher;
import de.pandigo.mountains.hateoas.HateoasAction;
import org.springframework.stereotype.Component;

import de.pandigo.controller.MountainController;
import de.pandigo.dto.Mountains;

@Component
public class MountainsEnricher extends AbstractEnricher<Mountains> {

    @Override
    public Mountains enrich(final Mountains mountains, final List<HateoasAction> actions) {
        mountains.add(linkTo(methodOn(MountainController.class).getAllMountains()).withSelfRel());
        return super.enrich(mountains, actions);
    }
}
