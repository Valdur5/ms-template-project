package de.pandigo.controller;

import de.pandigo.dto.Countries;
import de.pandigo.dto.Country;
import de.pandigo.mapper.CountryMapper;
import de.pandigo.services.CountryService;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * CountryController provides all the basic features to manage countries.
 */
@Api(name = "Country services", description = "Methods for managing countries", visibility = ApiVisibility.PUBLIC, stage = ApiStage.ALPHA)
@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryMapper countryMapper;
    /**
     * REST method for getting all the existing countries.
     *
     * @return - An array of country objects in JSON format.
     */
    @ApiMethod(description = "Method for getting all the existing countries.")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiErrors(apierrors = {
            @ApiError(code = "500", description = "Internal Server error.")
    })
    public @ApiResponseObject()
    Countries getAllCountries() {
        return countryMapper.mapEntitiesToDTO(countryService.getAllCountries());
    }

    /**
     * REST method for retrieving a specific country.
     *
     * @param countryId - The unique identifier of the country.
     * @return - The country in JSON format.
     */
    @ApiMethod(description = "Method for retrieving a specific country.")
    @RequestMapping(value = "/{countryId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiErrors(apierrors = {
            @ApiError(code = "404", description = "Resource not found."),
            @ApiError(code = "500", description = "Internal Server error.")
    })
    public @ApiResponseObject()
    Country getCountry(
            @ApiPathParam(name = "countryId", description = "The unique identifier of the country")
            @PathVariable long countryId) {
        return countryMapper.mapEntityToDTO(countryService.getCountry(countryId));
    }

}
