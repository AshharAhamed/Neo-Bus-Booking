package com.neo.ticketingapp.service.interfaces;

import com.neo.ticketingapp.model.User;
import java.util.List;

public interface UserService {

    User getUserById(String userId);

    /**
     * Add new user to the system
     * @param user details of new user
     */
    String insertUser(User user);

    /**
     * Use to reset the logging password of the user
     * @param username of the user
     * @param currentPassword of the user
     * @param newPassword of the user
     * @throws IllegalAccessException if user doesn't exist
     */
    void resetPassword(String username,String currentPassword,String newPassword) throws IllegalAccessException;


    String updateUserDetails(User user)  throws IllegalAccessException;

    /**
     * Delete the User details from the system
     * @param username of the user
     * @throws IllegalAccessException if user is not exist
     */
    void deleteUser(String username) throws IllegalAccessException;

    /**
     * Get all the service users
     * @return att the service users
     */

    User logUser(String username, String password) throws IllegalAccessException;

    List<User> getAllUsers(String userType);
}
