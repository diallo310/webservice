package com.infotel.webservice.model;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * <p>
 * User is entity using to create user
 * </p>
 *
 * @author Diallo
 *
 */

@Document(collection = "users")
public class User {
	@Id
	@Field(value = "_id")
	private String id;

	@NotEmpty(message = "FirstName can not be null or blank")
	private String firstName;

	@NotEmpty(message = "LastName can not be null or blank")
	private String lastName;
    
	@Email
	@Indexed(unique = true)
	private String email;
	@NotNull(message = "Age can not be null")
	@Min(18)
	private int age;

	@Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "Mobile number is invalid")
	private String phoneNumber;

	private boolean isActive;

	@NotNull(message = "Address can not be null")
	@Valid
	private Address address;

	/**
	 * Constructor
	 */
	public User() {
		super();
	}

	/**
	 * Constructor
	 *
	 * @param firstName   (require) user firstname
	 * @param lastName    (require) user last name
	 * @param email       (require) user email
	 * @param age         (require) user age
	 * @param phoneNumber (optional) user phone number
	 *
	 */
	public User(String firstName, String lastName, String email, int age, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Constructor
	 *
	 * @param firstName   (require) user firstname
	 * @param lastName    (require) user last name
	 * @param email       (require) user email
	 * @param age         (require) user age
	 * @param phoneNumber (optional) user phone number
	 * @param address     (require) user address
	 */

	public User(String firstName, String lastName, String email, int age, String phoneNumber, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	/**
	 * 
	 *
	 * @param firstName   (require) user firstname
	 * @param lastName    (require) user last name
	 * @param email       (require) user email
	 * @param age         (require) user age
	 * @param phoneNumber (optional) user phone number
	 * @param isActive    (optional) user status
	 * @param address     (require) user address
	 */

	public User(String firstName, String lastName, String email, int age, String phoneNumber, boolean isActive,
			Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.isActive = isActive;
		this.address = address;
	}

	/**
	 *
	 * @return the identity of user
	 */
	public String getId() {
		return id;
	}

	/**
	 *
	 * @param id idendity of user
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * @return the first name user
	 */

	public String getFirstName() {
		return firstName;
	}

	/**
	 *
	 * @param firstName can not be null or blank
	 */

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 *
	 * @return the last name of the user
	 */

	public String getLastName() {
		return lastName;
	}

	/**
	 *
	 * @param lastName can not be null or blank
	 */

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 *
	 * @return the email of user
	 */

	public String getEmail() {
		return email;
	}

	/**
	 *
	 * @param email must be unique
	 */

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 *
	 * @return the age of user
	 */

	public int getAge() {
		return age;
	}

	/**
	 *
	 * @param age can not be null
	 */

	public void setAge(int age) {
		this.age = age;
	}

	/**
	 *
	 * @return the user phone numberd
	 */

	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 *
	 * @param phoneNumber can be ommitted
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 
	 * @return  isActive true or false
	 */

	public boolean isActive() {
		return isActive;
	}

	/**
	 *
	 * @param isActive can be ommitted
	 */

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 *
	 * @return the address of user
	 */

	public Address getAddress() {
		return address;
	}

	/**
	 *
	 * @param address can not be null
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

}
