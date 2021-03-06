package org.wscale.mountains.domain;

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

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MountainEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long mountainId;

	private String name;
	private int altitude;
	private int firstAscent;
	private String[] firstAscenders;

	public void setMountainId(final Long mountainId) {
		this.mountainId = mountainId;
	}

	public void setDateAdded(final LocalDate dateAdded) {
		this.dateAdded = dateAdded;
	}

	// Example for an attribute we don't want to expose to the public.
	private LocalDate dateAdded;

	protected MountainEntity() {

	}

	public MountainEntity(final String name, final int altitude, final int firstAscent,
	        final String[] firstAscenders, final LocalDate dateAdded) {
		this.name = name;
		this.altitude = altitude;
		this.firstAscent = firstAscent;
		this.firstAscenders = firstAscenders;
		this.dateAdded = dateAdded;
	}

	public Long getMountainId() {
		return this.mountainId;
	}

	public String getName() {
		return this.name;
	}

	public int getAltitude() {
		return this.altitude;
	}

	public int getFirstAscent() {
		return this.firstAscent;
	}

	public String[] getFirstAscenders() {
		return this.firstAscenders;
	}

	public LocalDate getDateAdded() {
		return this.dateAdded;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setAltitude(final int altitude) {
		this.altitude = altitude;
	}

	public void setFirstAscent(final int firstAscent) {
		this.firstAscent = firstAscent;
	}

	public void setFirstAscenders(final String[] firstAscenders) {
		this.firstAscenders = firstAscenders;
	}
}
