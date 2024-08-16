package com.gethealthy.userservice.service;

import com.gethealthy.userservice.model.User;

public interface UserService {
    User getUserById(Long id);
}
