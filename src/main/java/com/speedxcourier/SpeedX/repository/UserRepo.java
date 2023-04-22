package com.speedxcourier.SpeedX.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.speedxcourier.SpeedX.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    public User findUserByUsername(String username);
}
