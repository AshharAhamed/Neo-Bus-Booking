package com.neo.ticketingapp.service.interfaces;

import com.neo.ticketingapp.model.User;
import java.util.List;

public interface UserService {

    User getUserById(String userId);

    String insertUser(User user);

    void resetPassword(String username,String currentPassword,String newPassword) throws IllegalAccessException;

    String updateUserDetails(User user)  throws IllegalAccessException;

    void deleteUser(String username) throws IllegalAccessException;

    User logUser(String username, String password) throws IllegalAccessException;

    List<User> getAllUsers(String userType);

}
