package de.pandigo.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
public class MountainEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long mountainId;

	private String name;
	private int altitude;
	@OneToMany
	private List<CountryEntity> countries;

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

	public MountainEntity(final String name, final int altitude, final List<CountryEntity> countries, final int firstAscent,
	        final String[] firstAscenders, final LocalDate dateAdded) {
		this.name = name;
		this.altitude = altitude;
		this.countries = countries;
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

	public List<CountryEntity> getCountries() {
		return this.countries;
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

	public void setCountries(final List<CountryEntity> countries) {
		this.countries = countries;
	}

	public void setFirstAscent(final int firstAscent) {
		this.firstAscent = firstAscent;
	}

	public void setFirstAscenders(final String[] firstAscenders) {
		this.firstAscenders = firstAscenders;
	}
}
