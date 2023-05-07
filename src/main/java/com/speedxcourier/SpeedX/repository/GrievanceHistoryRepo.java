package com.speedxcourier.SpeedX.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.speedxcourier.SpeedX.domain.GrievanceHistory;

public interface GrievanceHistoryRepo extends JpaRepository<GrievanceHistory, Long> {
    
}
