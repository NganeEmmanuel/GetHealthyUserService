package com.gethealthy.userservice.service;

import com.gethealthy.userservice.exception.NoMatchingUserFoundException;
import com.gethealthy.userservice.exception.UserNotFoundException;
import com.gethealthy.userservice.model.User;
import com.gethealthy.userservice.model.UserDTO;
import jdk.jshell.spi.ExecutionControl;

public interface UserService {
    /**
     *
     * @param id Data type of lONG
     * @return a new user object if found
     * @throws UserNotFoundException if user is not found in the database
     */
    UserDTO getUserById(Long id) throws UserNotFoundException;

    /**
     *
     * @param user user object to be saved
     * @return userDTO of saved user
     * @throws  ExecutionControl.UserException if user was unable to be added to the database
     */
    UserDTO signup(User user) throws ExecutionControl.UserException;

    /**
     *
     * @param username string by which you want to filter by
     * @return userDTO of saved user
     * @throws  NoMatchingUserFoundException when user was not found in the database
     */
    UserDTO getUserByUsername(String username) throws NoMatchingUserFoundException;
}
