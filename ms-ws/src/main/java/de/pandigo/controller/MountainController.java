package de.pandigo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // TODO REMOVE THIS
    private final Mountains mountains = null;

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

        final Map<String, Object> actions = new HashMap<>();
        actions.put("countries", methodOn(CountryController.class).getAllCountries());
        actions.put("selectedMountains", methodOn(MountainController.class).getMountain(10));
        actions.put("selectedMountains", methodOn(MountainController.class).getMountain(12));

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
        mountain.add(linkTo(methodOn(MountainController.class).getMountain(mountainEntity.getId())).withSelfRel());

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


    private Mountains generateData() {
        final Country nepal = new Country("Nepal", 26424000);
        nepal.add(linkTo(methodOn(CountryController.class).getCountry(0)).withSelfRel());
        final Country tibet = new Country("Tibet", 5240504);
        tibet.add(linkTo(methodOn(CountryController.class).getCountry(1)).withSelfRel());
        final Country france = new Country("France", 66910000);
        france.add(linkTo(methodOn(CountryController.class).getCountry(2)).withSelfRel());
        final Country italy = new Country("Italy", 60599000);
        italy.add(linkTo(methodOn(CountryController.class).getCountry(3)).withSelfRel());

        final Mountain montblanc = new Mountain("Mont Blanc", 4200);
        montblanc.setCountries(Arrays.asList(france, italy));
        montblanc.setFirstAscent(1786);
        montblanc.setFirstAscenders(new String[]{"Jacques Balmat", "Michel-Gabriel Paccard"});
        montblanc.add(linkTo(methodOn(MountainController.class).getMountain(0)).withSelfRel());
        montblanc.add(linkTo(methodOn(MountainController.class).getAllMountains()).withRel("mountains"));

        final Mountain mounteverest = new Mountain("Mount Everest", 8848);
        mounteverest.setCountries(Arrays.asList(nepal,tibet));
        mounteverest.setFirstAscent(1953);
        mounteverest.setFirstAscenders(new String[]{"Edmund Hillary", "Tenzing Norgay"});
        mounteverest.add(linkTo(methodOn(MountainController.class).getMountain(1)).withSelfRel());
        mounteverest.add(linkTo(methodOn(MountainController.class).getAllMountains()).withRel("mountains"));

        final Mountain lhotse = new Mountain("Lhotse", 8516);
        lhotse.setCountries(Arrays.asList(nepal,tibet));
        lhotse.setFirstAscent(1956);
        lhotse.setFirstAscenders(new String[]{"Ernst Reiss", "Fritz Luchsinger"});
        lhotse.add(linkTo(methodOn(MountainController.class).getMountain(2)).withSelfRel());
        lhotse.add(linkTo(methodOn(MountainController.class).getAllMountains()).withRel("mountains"));

        final Mountain chopolu = new Mountain("Cho Polu", 6735);
        chopolu.setCountries(Arrays.asList(nepal));
        chopolu.setFirstAscent(1984);
        chopolu.setFirstAscenders(new String[]{"Nil Bohigas"});
        chopolu.add(linkTo(methodOn(MountainController.class).getMountain(3)).withSelfRel());
        chopolu.add(linkTo(methodOn(MountainController.class).getAllMountains()).withRel("mountains"));

        final List<Mountain> mountainList = new ArrayList<>();
        mountainList.add(montblanc);
        mountainList.add(mounteverest);
        mountainList.add(lhotse);
        mountainList.add(chopolu);
        final Mountains mountains = new Mountains(mountainList);
        mountains.add(linkTo(methodOn(MountainController.class).getAllMountains()).withSelfRel());
        mountains.add(linkTo(methodOn(CountryController.class).getAllCountries()).withRel("countries"));
        return mountains;
    }
}
