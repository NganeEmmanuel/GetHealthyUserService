package com.gethealthy.userservice.service;

import com.gethealthy.userservice.model.User;
import com.gethealthy.userservice.model.UserDTO;
import jdk.jshell.spi.ExecutionControl;

public interface UserService {
    User getUserById(Long id);

    /**
     *
     * @param user user object to be saved
     * @return userDTO of saved user
     * @throws  ExecutionControl.UserException
     */
    UserDTO signup(User user) throws ExecutionControl.UserException;
}
