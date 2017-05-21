package de.pandigo.controller;

import de.pandigo.dto.Countries;
import de.pandigo.dto.Country;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * CountryController provides all the basic features to manage countries.
 */
@Api(name = "Country services", description = "Methods for managing countries", visibility = ApiVisibility.PUBLIC, stage = ApiStage.ALPHA)
@RestController
@RequestMapping("/countries")
public class CountryController {

    private Countries countries = null;

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

        if(countries == null) {
            setupData();
        }

        return countries;
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
            @PathVariable int countryId) {
        if(countries == null) {
            setupData();
        }
        return countries.getCountries().get(countryId);
    }

    private void setupData(){
        Country nepal = new Country("Nepal", 26424000);
        nepal.add(linkTo(methodOn(CountryController.class).getCountry(0)).withSelfRel());
        nepal.add(linkTo(methodOn(CountryController.class).getAllCountries()).withRel("countries"));
        Country tibet = new Country("Tibet", 5240504);
        tibet.add(linkTo(methodOn(CountryController.class).getCountry(1)).withSelfRel());
        tibet.add(linkTo(methodOn(CountryController.class).getAllCountries()).withRel("countries"));
        Country france = new Country("France", 66910000);
        france.add(linkTo(methodOn(CountryController.class).getCountry(2)).withSelfRel());
        france.add(linkTo(methodOn(CountryController.class).getAllCountries()).withRel("countries"));
        Country italy = new Country("Italy", 60599000);
        italy.add(linkTo(methodOn(CountryController.class).getCountry(3)).withSelfRel());
        italy.add(linkTo(methodOn(CountryController.class).getAllCountries()).withRel("countries"));

        countries = new Countries();
        countries.addCountry(nepal);
        countries.addCountry(tibet);
        countries.addCountry(france);
        countries.addCountry(italy);
        countries.add(linkTo(methodOn(CountryController.class).getAllCountries()).withSelfRel());
        countries.add(linkTo(methodOn(MountainController.class).getAllMountains()).withRel("mountains"));
    }
}
