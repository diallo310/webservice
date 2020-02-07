package com.infotel.webservice.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Address {

	@NotEmpty(message = "Country can not be null or blank")
	@Pattern(regexp = "France", message = "Country should be France")

	private String country;
	@NotEmpty(message = "City can not be null or blank")
	private String city;
	
	@Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{2})$", message = "zipCode is invalid")
	@NotNull(message = "ZipCode  can not be null")
	private String zipCode;

	@NotEmpty(message = "Street can not be null or blank")
	private String street;

	@NotNull(message = "StreetNumber  can not be null")
	@Min(1)
	private int streetNumber;

	/**
	 * Constructor
	 */
	public Address() {
		super();
// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 *
	 * @param country      can not be null or blank
	 * @param city         can not be null or blank
	 * @param zipCode      can not be null
	 * @param street       can not be null or blank
	 * @param streetNumber can not be null and greater than 0
	 */
	public Address(String country, String city, String zipCode, String street, int streetNumber) {
		super();
		this.country = country;
		this.city = city;
		this.zipCode = zipCode;
		this.street = street;
		this.streetNumber = streetNumber;
	}

	/**
	 *
	 * @return the country of
	 */
	public String getCountry() {
		return country;
	}

	/**
	 *
	 * @param country can not be null or blank
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 *
	 * @return the city of
	 */
	public String getCity() {
		return city;
	}

	/**
	 *
	 * @param city can not be null or blank
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 *
	 * @return the user of zip code
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 *
	 * @param zipCode can not be null
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 *
	 * @return the user of street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 *
	 * @param street can not be null or blank
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 *
	 * @return the user of street number
	 */
	public int getStreetNumber() {
		return streetNumber;
	}

	/**
	 *
	 * @param streetNumber can not be null and greater than 0
	 */
	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

}