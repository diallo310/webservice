package com.infotel.webservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.infotel.webservice.model.Address;
import com.infotel.webservice.model.User;
import com.infotel.webservice.repository.UserRepository;
import com.infotel.webservice.service.UserService;

@SpringBootTest

class WebserviceApplicationTests {
	
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userDao;
	
	protected MockMvc mvc;
	   
	@Test
	public void getProfile() {	
		String email = "jdoe0@gmail.com";
		when(userDao.findUserByEmail(email)).thenReturn(new User("john","doe","jdoe310@gmail.com",18,"07341863"));
		assertNotNull(userService.findUserByEmail(email));	
	}
	
	@Test
	public void register() {
        Address address = new Address("France", "Nice", "06000", "Massena", 4);
		User user = new User("Mia", "Barry", "barry@gmail.com",27,"07341863", address);
		when(userDao.save(user)).thenReturn(user);
		assertEquals(user, userDao.save(user));
	}


}
