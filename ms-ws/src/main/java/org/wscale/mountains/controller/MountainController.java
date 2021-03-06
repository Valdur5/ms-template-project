package org.wscale.mountains.controller;

/*
 * Copyright 2017 Valentin Durst (www.wscale.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.wscale.commons.dto.ItemCollection;
import org.wscale.commons.hateoas.HateoasAction;
import org.wscale.commons.hateoas.ItemCollectionEnhancer;
import org.wscale.mountains.domain.MountainEntity;
import org.wscale.mountains.dto.Mountain;
import org.wscale.mountains.hateoas.ActionType;
import org.wscale.mountains.hateoas.MountainNavigationEnhancer;
import org.wscale.mountains.mapper.MountainMapper;
import org.wscale.mountains.merger.MountainMerger;
import org.wscale.mountains.service.MountainService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

	@Autowired
	private MountainMerger mountainMerger;

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

		// Enhance the mountain with a reference to the complete mountain list.
		for (int i = 0; i < mountains.getItems().size(); i++) {
			Mountain mountain = mountains.getItems().get(i);
			mountain = this.itemEnricher.enhance(mountain,
					methodOn(MountainController.class).getMountain(mountain.getMountainId()),
					new HateoasAction(ActionType.showall.toString(), methodOn(MountainController.class).getAllMountains()));
			mountains.getItems().set(i,mountain);
		}

		// Enhance the itemCollection object with a self reference.
		return this.itemCollectionEnricher.enhance(mountains, methodOn(MountainController.class).getAllMountains());
	}

	/**
	 * REST method for adding a new mountain to the mountains.
	 *
	 * @param mountain - The mountain payload object in JSON format.
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Mountain addMountain(@Valid @RequestBody final Mountain mountain) {
		// Convert the mountain DTO object into an mountain entry object.
		MountainEntity mountainEntity = this.mountainMapper.mapDTOToEntity(mountain);
		// Create add the creation date which is not present in the DTO.
		mountainEntity.setDateAdded(LocalDate.now());
		// Add the new mountain.
		mountainEntity = this.mountainService.addMountain(mountainEntity);
		// Convert the mountain entry back into an DTO object to return to the client.
		Mountain returnMountain = this.mountainMapper.mapEntityToDTO(mountainEntity);
		// Enhance the mountain DTO object with a reference to all mountains.
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
		// Get the mountain from the mountain service and convert it to an DTO object.
		final Mountain mountain = this.mountainMapper.mapEntityToDTO(this.mountainService.getMountain(mountainId));
		// Enhance it with a navigational features.
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
	public void updateMountain(@PathVariable final long mountainId, @Valid @RequestBody final Mountain mountain) {
		// Get mountain from from the request and convert it to an mountain entity.
		final MountainEntity mountainEntity = this.mountainMapper.mapDTOToEntity(mountain);
		// Update the mountain in the mountain services.
		this.mountainService.updateMountain(mountainId, mountainEntity);
	}

	/**
	 * REST method for partially update a mountain. Only fields which have a value will be
	 * overridden.
	 *
	 * @param mountainId - The unique identifier of the mountain.
	 * @param mountain - The mountain payload object in JSON format.
	 */
	@RequestMapping(value = "/{mountainId}", method = RequestMethod.PATCH)
	@ResponseStatus(HttpStatus.OK)
	public void patchMountain(@PathVariable final long mountainId, @RequestBody final Mountain mountain) {
		// Get mountain from from the request and convert it to an mountain entity.
		final MountainEntity mountainEntity = this.mountainMapper.mapDTOToEntity(mountain);
		// Update the mountain in the mountain services.
		this.mountainService.patchMountain(mountainId, mountainEntity);
	}

	/**
	 * REST method for deleting a specific mountain.
	 *
	 * @param mountainId - The unique identifier of the mountain.
	 */
	@RequestMapping(value = "/{mountainId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteMountain(@PathVariable final long mountainId) {
		// Delete the mountain with the mountainId from the mountains services.
		this.mountainService.deleteMountain(mountainId);
	}

}
