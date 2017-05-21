package de.pandigo.controller;

import de.pandigo.dto.Mountain;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * MountainController provides all the basic features to manage mountains.
 */

@RestController
@RequestMapping("/mountains")
public class MountainController {

    /**
     * REST method for getting all the existing mountains.
     * @return - An array of mountain objects in JSON format.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Mountain> getAllMountains() {
        return Arrays.asList(new Mountain("Mont Blanc", 4200, new String[]{"Schweiz", "Italien"}));
    }

    /**
     * REST method for adding a new mountain to the mountains.
     * @param mountain - The mountain payload object in JSON format.
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void getAllMountains(Mountain mountain) {

    }

    /**
     * REST method for retrieving a specific mountain.
     * @param mountainId - The unique identifier of the mountain.
     * @return - The mountain in JSON format.
     */
    @RequestMapping(value = "/{mountainId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Mountain getMountain(@PathVariable String mountainId) {
        return new Mountain("Mont Blanc", 4200, new String[]{"Schweiz", "Italien"});
    }

    /**
     * REST method for a full update on a mountain. All fields will be overridden empty or not.
     * @param mountainId - The unique identifier of the mountain.
     * @param mountain - The mountain payload object in JSON format.
     */
    @RequestMapping(value = "/{mountainId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMountain(@PathVariable String mountainId, Mountain mountain) {

    }

    /**
     * REST method for partially update a mountain. Only fields which have a value will be overridden, fields which
     * don't have a value (no value = null or empty string) will be taken from the already existing object.
     * @param mountainId - The unique identifier of the mountain.
     * @param mountain - The mountain payload object in JSON format.
     */
    @RequestMapping(value = "/{mountainId}", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchMountain(@PathVariable String mountainId, Mountain mountain) {

    }

    /**
     * REST method for deleting a specific mountain.
     * @param mountainId - The unique identifier of the mountain.
     */
    @RequestMapping(value = "/{mountainId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMountain(@PathVariable String mountainId) {

    }
}
