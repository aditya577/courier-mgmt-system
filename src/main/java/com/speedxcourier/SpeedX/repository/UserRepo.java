package com.speedxcourier.SpeedX.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.speedxcourier.SpeedX.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    public User findUserByUsername(String username);

    @Query(value = "select u.* from users u join user_details ud on ud.user_id = u.id where ud.role='CUSTOMER' ", nativeQuery = true)
    public List<User> getAllCustomerUsers();
}
