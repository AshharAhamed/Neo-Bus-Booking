package com.neo.ticketingapp.repository;

import com.neo.ticketingapp.enums.UserType;
import com.neo.ticketingapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    List<User> findByUsername(String username);
    List<User> findByType(UserType type);
}
