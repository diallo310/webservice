package com.infotel.webservice.service;

import org.springframework.stereotype.Service;

import com.infotel.webservice.model.User;

@Service
public interface UserService {
	public void register(User user);
	public User findUserByEmail(String email);

}
