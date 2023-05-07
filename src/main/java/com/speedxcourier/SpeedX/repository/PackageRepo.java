package com.speedxcourier.SpeedX.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.speedxcourier.SpeedX.domain.Package;

public interface PackageRepo extends JpaRepository<Package, Long>{
    
    public List<Package> getPackagesByUserId(Long userId);
}
