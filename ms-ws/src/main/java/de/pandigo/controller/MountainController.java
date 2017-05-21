package de.pandigo.controller;

import de.pandigo.dto.Mountain;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * MountainController provides all the basic features to manage mountains.
 */
@Api(name = "Mountain services", description = "Methods for managing mountains", visibility = ApiVisibility.PUBLIC, stage = ApiStage.ALPHA)
@RestController
@RequestMapping("/mountains")
public class MountainController {

    /**
     * REST method for getting all the existing mountains.
     * @return - An array of mountain objects in JSON format.
     */
    @ApiMethod(description = "Method for getting all the existing mountains.")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiErrors(apierrors={
            @ApiError(code="500", description="Internal Server error.")
    })
    public @ApiResponseObject() List<Mountain> getAllMountains() {
        return Arrays.asList(new Mountain("Mont Blanc", 4200, new String[]{"Schweiz", "Italien"}));
    }

    /**
     * REST method for adding a new mountain to the mountains.
     * @param mountain - The mountain payload object in JSON format.
     */
    @ApiMethod(description = "Method for adding a new mountain to the mountains.")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiErrors(apierrors={
            @ApiError(code="500", description="Internal Server error.")
    })
    public void getAllMountains(@ApiBodyObject @RequestBody Mountain mountain) {

    }

    /**
     * REST method for retrieving a specific mountain.
     * @param mountainId - The unique identifier of the mountain.
     * @return - The mountain in JSON format.
     */
    @ApiMethod(description = "Method for retrieving a specific mountain.")
    @RequestMapping(value = "/{mountainId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiErrors(apierrors={
            @ApiError(code="404", description="Resource not found."),
            @ApiError(code="500", description="Internal Server error.")
    })
    public @ApiResponseObject Mountain getMountain(
            @ApiPathParam(name = "mountainId", description = "The unique identifier of the mountain")
            @PathVariable String mountainId) {
        return new Mountain("Mont Blanc", 4200, new String[]{"Schweiz", "Italien"});
    }

    /**
     * REST method for a full update on a mountain. All fields will be overridden empty or not.
     * @param mountainId - The unique identifier of the mountain.
     * @param mountain - The mountain payload object in JSON format.
     */
    @ApiMethod(description = "Method for a full update on a mountain. All fields will be overridden empty or not.")
    @RequestMapping(value = "/{mountainId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ApiErrors(apierrors={
            @ApiError(code="404", description="Resource not found."),
            @ApiError(code="500", description="Internal Server error.")
    })
    public void updateMountain(@ApiPathParam(name = "mountainId", description = "The unique identifier of the mountain")
                                   @PathVariable String mountainId,
                               @ApiBodyObject @RequestBody Mountain mountain) {

    }

    /**
     * REST method for partially update a mountain. Only fields which have a value will be overridden, fields which
     * don't have a value (no value = null, 0 or empty string) will be taken from the already existing object.
     * @param mountainId - The unique identifier of the mountain.
     * @param mountain - The mountain payload object in JSON format.
     */
    @ApiMethod(description = "Method for partially update a mountain. Only fields which have a value will be " +
            "overridden, fields which don't have a value (no value = null, 0 or empty string) will be taken from the " +
            "already existing object.")
    @RequestMapping(value = "/{mountainId}", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    @ApiErrors(apierrors={
            @ApiError(code="404", description="Resource not found."),
            @ApiError(code="500", description="Internal Server error.")
    })
    public void patchMountain(@ApiPathParam(name = "mountainId", description = "The unique identifier of the mountain")
                                  @PathVariable String mountainId,
                              @ApiBodyObject @RequestBody Mountain mountain) {

    }

    /**
     * REST method for deleting a specific mountain.
     * @param mountainId - The unique identifier of the mountain.
     */
    @ApiMethod(description = "Method for deleting a specific mountain.")
    @RequestMapping(value = "/{mountainId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ApiErrors(apierrors={
            @ApiError(code="404", description="Resource not found."),
            @ApiError(code="500", description="Internal Server error.")
    })
    public void deleteMountain(@ApiPathParam(name = "mountainId", description = "The unique identifier of the mountain")
                                   @PathVariable String mountainId) {

    }
}
