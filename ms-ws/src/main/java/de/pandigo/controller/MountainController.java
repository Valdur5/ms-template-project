package de.pandigo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.pandigo.hateoas.ActionType;
import de.pandigo.hateoas.HateoasAction;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiError;
import org.jsondoc.core.annotation.ApiErrors;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.pandigo.domain.MountainEntity;
import de.pandigo.dto.Country;
import de.pandigo.dto.Mountain;
import de.pandigo.dto.Mountains;
import de.pandigo.hateoas.MountainsEnricher;
import de.pandigo.mapper.MountainMapper;
import de.pandigo.services.MountainService;

/**
 * MountainController provides all the basic features to manage mountains.
 */
@Api(name = "Mountain services", description = "Methods for managing mountains", visibility = ApiVisibility.PUBLIC, stage = ApiStage.ALPHA)
@RestController
@RequestMapping("/mountains")
public class MountainController {

    @Autowired
    private MountainService mountainService;

    @Autowired
    private MountainMapper mountainMapper;

    @Autowired
    private MountainsEnricher mountainsEnricher;

    public MountainController() {
    }

    /**
     * REST method for getting all the existing mountains.
     *
     * @return - An array of mountain objects in JSON format.
     */
    @ApiMethod(description = "Method for getting all the existing mountains.")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiErrors(apierrors = {
            @ApiError(code = "500", description = "Internal Server error.")
    })
    public @ApiResponseObject()
    Mountains getAllMountains() {
        // Get all the mountains from our business layer.
        final Mountains mountains = this.mountainMapper.mapEntitiesToDTO(this.mountainService.getAllMountains());

        List<HateoasAction> actions = new ArrayList<>();
        actions.add(new HateoasAction(ActionType.showall, Arrays.asList(
                methodOn(CountryController.class).getAllCountries(),
                methodOn(MountainController.class).getAllMountains()
        )));
        actions.add(new HateoasAction(ActionType.back, methodOn(MountainController.class).getMountain(10)));

        return this.mountainsEnricher.enrich(mountains, actions);
    }

    /**
     * REST method for adding a new mountain to the mountains.
     *
     * @param mountain - The mountain payload object in JSON format.
     */
    @ApiMethod(description = "Method for adding a new mountain to the mountains.")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiErrors(apierrors = {
            @ApiError(code = "500", description = "Internal Server error.")
    })
    public @ApiResponseObject() Mountain addMountain(@ApiBodyObject @RequestBody final Mountain mountain) {

        MountainEntity mountainEntity = this.mountainMapper.mapDTOToEntity(mountain);
        mountainEntity.setDateAdded(LocalDate.now());
        mountainEntity = this.mountainService.addMountain(mountainEntity);
        mountain.add(linkTo(methodOn(MountainController.class).getMountain(mountainEntity.getMountainId())).withSelfRel());

        return mountain;
    }

    /**
     * REST method for retrieving a specific mountain.
     *
     * @param mountainId - The unique identifier of the mountain.
     * @return - The mountain in JSON format.
     */
    @ApiMethod(description = "Method for retrieving a specific mountain.")
    @RequestMapping(value = "/{mountainId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiErrors(apierrors = {
            @ApiError(code = "404", description = "Resource not found."),
            @ApiError(code = "500", description = "Internal Server error.")
    })
    public @ApiResponseObject()
    Mountain getMountain(
            @ApiPathParam(name = "mountainId", description = "The unique identifier of the mountain")
            @PathVariable final long mountainId) {
        //return this.mountains.getMountains().get(mountainId);
        return null;
    }

    /**
     * REST method for a full update on a mountain. All fields will be overridden empty or not.
     *
     * @param mountainId - The unique identifier of the mountain.
     * @param mountain   - The mountain payload object in JSON format.
     */
    @ApiMethod(description = "Method for a full update on a mountain. All fields will be overridden empty or not.")
    @RequestMapping(value = "/{mountainId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ApiErrors(apierrors = {
            @ApiError(code = "404", description = "Resource not found."),
            @ApiError(code = "500", description = "Internal Server error.")
    })
    public void updateMountain(@ApiPathParam(name = "mountainId", description = "The unique identifier of the mountain")
                               @PathVariable final int mountainId,
                               @ApiBodyObject @RequestBody final Mountain mountain) {
        //this.mountains.setMountain(mountainId, mountain);
    }

    /**
     * REST method for partially update a mountain. Only fields which have a value will be overridden, fields which
     * don't have a value (no value = null, 0 or empty string) will be taken from the already existing object.
     *
     * @param mountainId - The unique identifier of the mountain.
     * @param mountain   - The mountain payload object in JSON format.
     */
    @ApiMethod(description = "Method for partially update a mountain. Only fields which have a value will be " +
            "overridden, fields which don't have a value (no value = null, 0 or empty string) will be taken from the " +
            "already existing object.")
    @RequestMapping(value = "/{mountainId}", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    @ApiErrors(apierrors = {
            @ApiError(code = "404", description = "Resource not found."),
            @ApiError(code = "500", description = "Internal Server error.")
    })
    public void patchMountain(@ApiPathParam(name = "mountainId", description = "The unique identifier of the mountain")
                              @PathVariable final int mountainId,
                              @ApiBodyObject @RequestBody final Mountain mountain) {
        //this.mountains.setMountain(mountainId, mountain); // TODO this is no patch
    }

    /**
     * REST method for deleting a specific mountain.
     *
     * @param mountainId - The unique identifier of the mountain.
     */
    @ApiMethod(description = "Method for deleting a specific mountain.")
    @RequestMapping(value = "/{mountainId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ApiErrors(apierrors = {
            @ApiError(code = "404", description = "Resource not found."),
            @ApiError(code = "500", description = "Internal Server error.")
    })
    public void deleteMountain(@ApiPathParam(name = "mountainId", description = "The unique identifier of the mountain")
                               @PathVariable final int mountainId) {
        //this.mountains.deleteMountain(mountainId);
    }
}
