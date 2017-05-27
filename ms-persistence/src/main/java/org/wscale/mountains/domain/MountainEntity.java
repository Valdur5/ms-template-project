package org.wscale.mountains.domain;

import java.time.LocalDate;

import javax.persistence.*;

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
