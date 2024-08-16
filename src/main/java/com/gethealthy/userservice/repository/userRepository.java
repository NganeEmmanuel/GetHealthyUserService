package com.gethealthy.userservice.repository;

import com.gethealthy.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User, Long> {
}
