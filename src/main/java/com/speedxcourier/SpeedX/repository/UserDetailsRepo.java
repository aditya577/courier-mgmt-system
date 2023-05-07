package com.speedxcourier.SpeedX.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.speedxcourier.SpeedX.domain.UserDetails;

public interface UserDetailsRepo extends JpaRepository<UserDetails, Long> {
    
    public UserDetails findByEmailOrPhone(String email, String phone);

    public UserDetails findByUserId(Long userId);
}
