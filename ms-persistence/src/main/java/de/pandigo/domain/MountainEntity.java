package de.pandigo.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MountainEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String name;
	private int altitude;
	@OneToMany(fetch = FetchType.LAZY)
	private List<CountryEntity> countries;
	private int firstAscent;
	private String[] firstAscenders;

	public void setId(final Long id) {
		this.id = id;
	}

	public void setDateAdded(final LocalDate dateAdded) {
		this.dateAdded = dateAdded;
	}

	// Example for an attribute we don't want to expose to the public.
	private LocalDate dateAdded;

	protected MountainEntity() {

	}

	public MountainEntity(final Long id, final String name, final int altitude, final List<CountryEntity> countries, final int firstAscent,
	        final String[] firstAscenders, final LocalDate dateAdded) {
	    this.id = id;
		this.name = name;
		this.altitude = altitude;
		this.countries = countries;
		this.firstAscent = firstAscent;
		this.firstAscenders = firstAscenders;
		this.dateAdded = dateAdded;
	}

	public Long getId() {
		return this.id;
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
