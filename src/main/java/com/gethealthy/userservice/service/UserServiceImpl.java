package com.gethealthy.userservice.service;

import com.gethealthy.userservice.enums.UserAuthority;
import com.gethealthy.userservice.exception.NoMatchingUserFoundException;
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
    public UserDTO getUserById(Long id) throws UserNotFoundException {
        try {
            var user = userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException(id));
            return userWrapperService.toDTO(user);
        }catch (UserNotFoundException userNotFoundException){
            logger.info("Error getting user with id: {}", id);
            throw new RuntimeException(userNotFoundException);
        }catch (Exception e){
            logger.info("An error occurred getting user with id: {}", id);
            throw new RuntimeException((e));
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

    @Override
    public UserDTO getUserByUsername(String username) throws NoMatchingUserFoundException {
        try {
            var user =  userRepository.findByUsername(username)
                    .orElseThrow(() -> new NoMatchingUserFoundException(username));
            return userWrapperService.toDTO(user);
        }catch (UserNotFoundException userNotFoundException){
            logger.info("Error getting user with username: {}", username);
            throw new RuntimeException(userNotFoundException);
        }catch (Exception e){
            logger.info("An error occurred getting user with username: {}", username);
            throw new RuntimeException((e));
        }
    }

    @Override
    public UserDTO updatedUser(UserDTO userDTO) throws UserNotFoundException {
        try{
            var user = userRepository.findById(userDTO.getId()).orElseThrow(
                    () -> new UserNotFoundException(userDTO.getId())
            );

            userWrapperService.updateEntity(userDTO,user);
            userRepository.save(user);
            return userWrapperService.toDTO(user);

        }catch (UserNotFoundException userNotFoundException){
            logger.info("Error getting user during update with id: {}", userDTO.getId());
            throw new RuntimeException(userNotFoundException);
        }catch (Exception e){
            logger.info("An error occurred getting user during update with id: {}", userDTO.getId());
            throw new RuntimeException((e));
        }
    }
}
