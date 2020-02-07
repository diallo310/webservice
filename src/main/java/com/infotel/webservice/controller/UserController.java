package com.infotel.webservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infotel.webservice.execption.RessourceNotFoundException;
import com.infotel.webservice.execption.UserException;
import com.infotel.webservice.model.User;
import com.infotel.webservice.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("API for CRUD operations on users")
@RestController
public class UserController {

	@Autowired
	private UserService useService;

	@PostMapping(value = "users/register")
	@ApiOperation(value = "Add an User")
	public ResponseEntity<User> register(@Valid @RequestBody User user) throws UserException {
		try {
			useService.register(user);
		} catch (Exception e) {
			throw new UserException("Could not create user");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping(value = "users/profile/{email}")
	@ApiOperation(value = "Return  user by  email")
	public User profile(@PathVariable(name = "email") String email,
			@RequestParam(value = "active", required = false, defaultValue = "true") boolean isActive)
			throws RessourceNotFoundException {
		User user = useService.findUserByEmail(email);
		if (user == null || user.isActive() != isActive) {
			throw new RessourceNotFoundException("User not found");
		}
		return user;
	}

}
