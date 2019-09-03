package com.neo.ticketingapp.service.implementation;

import com.neo.ticketingapp.enums.UserType;
import com.neo.ticketingapp.model.User;
import com.neo.ticketingapp.repository.UserRepository;
import com.neo.ticketingapp.service.interfaces.UserService;

import java.util.List;
import java.util.Optional;

import com.neo.ticketingapp.validation.GeneralUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private GeneralUtils generalUtils;

    public UserServiceImpl() {
        this.generalUtils = new GeneralUtils();
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public String insertUser(User user) {
        logger.debug("Request to add New User received by the System");
        String result;
        if (!userRepository.findByUsername(user.getUsername()).isEmpty())
            return "Username already exist !";

        if (user.getUsername() == null)
            return "Username cannot be Empty !";
        if (!(result = generalUtils.isName(user.getFirstName(), "First Name")).equals("Valid"))
            return result;
        if (!(result = generalUtils.isName(user.getLastName(), "Last Name")).equals("Valid"))
            return result;
        if (!(result = generalUtils.isEmail(user.getEmail())).equals("Valid"))
            return result;
        if (!(result = generalUtils.isPhone(user.getContact())).equals("Valid"))
            return result;
        if (!(result = generalUtils.isPassword(user.getPassword())).equals("Valid"))
            return result;
        user.setPassword(generalUtils.encryptPassword(user.getPassword()));

        userRepository.insert(user);
        logger.info("New User {} Added", user.getUsername());
        return "User added Successfully !";
    }

    @Override
    public String updateUserDetails(User user) throws IllegalAccessException {
        logger.debug("Request to Update {} received by the System", user.getUsername());
        String result;
        User updatedUser;
        if ((updatedUser = getUserByUsername(user.getUsername())) == null)
            return "Username does not exist !";

        if (!(result = generalUtils.isEmail(user.getEmail())).equals("Valid"))
            return result;
        if (!(result = generalUtils.isPhone(user.getContact())).equals("Valid"))
            return result;
        if (!(result = generalUtils.isPassword(user.getPassword())).equals("Valid"))
            return result;

        updatedUser.setEmail(user.getEmail());
        updatedUser.setContact(user.getContact());
        updatedUser.setPassword(generalUtils.encryptPassword(user.getPassword()));

        userRepository.save(updatedUser);
        logger.info("New details are updated for the {}", user.getUsername());
        return "User Updated Successfully !";
    }

    public User logUser(String username, String password) throws IllegalAccessException {
        logger.debug("Request received to logging to the system by {}", username);

        if (username.isEmpty() || password.isEmpty()) {
            return null;
        }

        User user;
        if ((user = getUserByUsername(username)) != null) {
            if (user.getPassword().equals(generalUtils.encryptPassword(password))) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void deleteUser(String username) throws IllegalAccessException {
        logger.debug("Request received to delete the {}", username);
        User user = getUserByUsername(username);
        userRepository.delete(user);
        logger.info("{} is successfully deleted", username);
    }

    private User getUserByUsername(String username) throws IllegalAccessException {
        List<User> userList = userRepository.findByUsername(username);
        if (userList == null || userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public User getUserById(String userId) {
        logger.debug("Request received to get the User with Id - {}", userId);
        Optional<User> userById = userRepository.findById(userId);
        return userById.get();
    }

    @Override
    public void resetPassword(String username, String currentPassword, String newPassword) throws IllegalAccessException {
        logger.debug("Request received to reset the password from {}", username);

        List<User> userById = userRepository.findByUsername(username);
        User user = userById.get(0);

        user.setPassword(newPassword);
        userRepository.save(user);
        logger.info("Password is reset for {}", username);
    }

    @Override
    public List<User> getAllUsers(String userType) {
        if (userType.equals("All"))
            return userRepository.findAll();
        else if (UserType.Inspector.toString().equals(userType))
            return userRepository.findByType(UserType.Inspector);
        else if (UserType.Manager.toString().equals(userType))
            return userRepository.findByType(UserType.Manager);
        return null;
    }
}
