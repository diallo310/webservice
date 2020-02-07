package com.infotel.webservice.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.infotel.webservice.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
	public User findUserByEmail(String email);
	
}