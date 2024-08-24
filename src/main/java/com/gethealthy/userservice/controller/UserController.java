package com.gethealthy.userservice.controller;

import com.gethealthy.userservice.exception.NoMatchingUserFoundException;
import com.gethealthy.userservice.model.User;
import com.gethealthy.userservice.model.UserDTO;
import com.gethealthy.userservice.service.UserService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<UserDTO> getUserById(@RequestParam Long id) {

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody User user) throws ExecutionControl.UserException {
        return ResponseEntity.ok(userService.signup(user));
    }

    @GetMapping("/users")
    public ResponseEntity<UserDTO> getUserByUsername(@RequestParam String username) throws NoMatchingUserFoundException {

        return ResponseEntity.ok(userService.getUserByUsername(username));
    }


}
