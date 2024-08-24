package com.gethealthy.userservice.service;

import com.gethealthy.userservice.enums.UserAuthority;
import com.gethealthy.userservice.exception.UserNotFoundException;
import com.gethealthy.userservice.model.User;
import com.gethealthy.userservice.model.UserDTO;
import com.gethealthy.userservice.repository.UserRepository;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final MapperService<UserDTO, User> userWrapperService;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        try {
            return userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException(id));
        }catch (UserNotFoundException userNotFoundException){
            logger.info("Error getting user with id: {}", id);
            throw new RuntimeException();
        }
    }


    @Override
    public UserDTO signup(User user) throws ExecutionControl.UserException {
        user.setAuthority(UserAuthority.USER);
        try {
            userRepository.save(user);
            return userWrapperService.toDTO(user);
        }catch (Exception e){
            logger.info("Error saving user information: {}", user);
            throw new ExecutionControl.UserException("Error saving user information", "User", e.getStackTrace());
        }
    }
}
