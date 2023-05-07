package com.speedxcourier.SpeedX.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.speedxcourier.SpeedX.domain.Report;

public interface ReportRepo extends JpaRepository<Report, Long>{
    
}
