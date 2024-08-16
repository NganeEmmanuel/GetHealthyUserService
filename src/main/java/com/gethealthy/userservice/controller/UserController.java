package com.gethealthy.userservice.controller;

import com.gethealthy.userservice.model.User;
import com.gethealthy.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    UserService userService;
    @GetMapping("/users")
    public ResponseEntity<User> getUserById(@RequestParam Long id) {

        return ResponseEntity.ok(userService.getUserById(id));
    }
}
