package com.gethealthy.userservice.service;

import com.gethealthy.userservice.enums.UserAuthority;
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
    private MapperService<UserDTO, User> userWrapperService;
    private UserRepository UserRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User getUserById(Long id) {
        return  userRepository.findById(id)
                .orElse(
                        null
                );
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
