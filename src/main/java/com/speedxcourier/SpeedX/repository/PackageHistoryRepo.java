package com.speedxcourier.SpeedX.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.speedxcourier.SpeedX.domain.PackageHistory;

public interface PackageHistoryRepo extends JpaRepository<PackageHistory, Long>{
}
