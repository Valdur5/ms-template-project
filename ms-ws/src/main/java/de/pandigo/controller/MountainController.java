package de.pandigo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.time.LocalDate;

import de.pandigo.hateoas.MountainNavigationEnhancer;
import de.pandigo.mountains.dto.ItemCollection;
import de.pandigo.hateoas.ActionType;
import de.pandigo.mountains.hateoas.ItemCollectionEnhancer;
import de.pandigo.mountains.hateoas.HateoasAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.pandigo.domain.MountainEntity;
import de.pandigo.dto.Mountain;
import de.pandigo.mapper.MountainMapper;
import de.pandigo.services.MountainService;

/**
 * MountainController provides all the basic features to manage mountains.
 */
@RestController
@RequestMapping("/mountains")
public class MountainController {

	@Autowired
	private MountainService mountainService;

	@Autowired
	private MountainMapper mountainMapper;

	@Autowired
	private MountainNavigationEnhancer itemEnricher;

	@Autowired
	private ItemCollectionEnhancer<Mountain> itemCollectionEnricher;

	public MountainController() {}

	/**
	 * REST method for getting all the existing mountains.
	 *
	 * @return - An array of mountain objects in JSON format.
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public ItemCollection<Mountain> getAllMountains() {
		// Get all the mountains from our business layer.
		final ItemCollection<Mountain> mountains =
				this.mountainMapper.mapEntitiesToDTO(this.mountainService.getAllMountains());

		for (int i = 0; i < mountains.getItems().size(); i++) {
			Mountain mountain = mountains.getItems().get(i);
			mountain = this.itemEnricher.enhance(mountain,
					methodOn(MountainController.class).getMountain(mountain.getMountainId()),
					new HateoasAction(ActionType.showall.toString(), methodOn(MountainController.class).getAllMountains()));
			mountains.getItems().set(i,mountain);
		}

		return this.itemCollectionEnricher.enhance(mountains, methodOn(MountainController.class).getAllMountains());
	}

	/**
	 * REST method for adding a new mountain to the mountains.
	 *
	 * @param mountain - The mountain payload object in JSON format.
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Mountain addMountain(final Mountain mountain) {

		MountainEntity mountainEntity = this.mountainMapper.mapDTOToEntity(mountain);
		mountainEntity.setDateAdded(LocalDate.now());
		mountainEntity = this.mountainService.addMountain(mountainEntity);
		Mountain returnMountain = this.mountainMapper.mapEntityToDTO(mountainEntity);
		returnMountain = this.itemEnricher.enhance(returnMountain,
				methodOn(MountainController.class).getMountain(returnMountain.getMountainId()),
				new HateoasAction(ActionType.showall.toString(), methodOn(MountainController.class).getAllMountains()));
		return returnMountain;
	}

	/**
	 * REST method for retrieving a specific mountain.
	 *
	 * @param mountainId - The unique identifier of the mountain.
	 * @return - The mountain in JSON format.
	 */
	@RequestMapping(value = "/{mountainId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Mountain getMountain(@PathVariable final long mountainId) {
		Mountain mountain = this.mountainMapper.mapEntityToDTO(this.mountainService.getMountain(mountainId));
		return this.itemEnricher.enhanceWithNavigation(mountain, getAllMountains().getItems(),
				methodOn(MountainController.class).getMountain(mountain.getMountainId()));
	}

	/**
	 * REST method for a full update on a mountain. All fields will be overridden empty or not.
	 *
	 * @param mountainId - The unique identifier of the mountain.
	 * @param mountain - The mountain payload object in JSON format.
	 */
	@RequestMapping(value = "/{mountainId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateMountain( @PathVariable final int mountainId, @RequestBody final Mountain mountain) {
		// this.mountains.setMountain(mountainId, mountain);
	}

	/**
	 * REST method for partially update a mountain. Only fields which have a value will be
	 * overridden, fields which
	 * don't have a value (no value = null, 0 or empty string) will be taken from the already
	 * existing object.
	 *
	 * @param mountainId - The unique identifier of the mountain.
	 * @param mountain - The mountain payload object in JSON format.
	 */
	@RequestMapping(value = "/{mountainId}", method = RequestMethod.PATCH)
	@ResponseStatus(HttpStatus.OK)
	public void patchMountain(@PathVariable final int mountainId, @RequestBody final Mountain mountain) {
		// this.mountains.setMountain(mountainId, mountain); // TODO this is no patch
	}

	/**
	 * REST method for deleting a specific mountain.
	 *
	 * @param mountainId - The unique identifier of the mountain.
	 */
	@RequestMapping(value = "/{mountainId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteMountain(@PathVariable final int mountainId) {
		// this.mountains.deleteMountain(mountainId);
	}
}
