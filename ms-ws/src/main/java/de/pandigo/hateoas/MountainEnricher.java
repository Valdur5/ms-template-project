package de.pandigo.hateoas;

import de.pandigo.controller.MountainController;
import de.pandigo.dto.Mountain;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class MountainEnricher extends AbstractEnricher<Mountain> {

    /**
     * Enriches the mountain DTO with a self reference and a variable list of actions.
     * @param mountain - The object we want to modify.
     * @param id - The id of the mountain.
     * @param actions - The variable list of HateoasAction objects which should be also added to the mountain.
     * @return - The enhanced mountain object.
     */
    public Mountain enrich(Mountain mountain, long id, List<HateoasAction> actions) {
        mountain.add(linkTo(methodOn(MountainController.class).getMountain(id)).withSelfRel());
        return super.enrich(mountain, actions);
    }
}
