package com.example.YTlogin.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.YTlogin.Entity.DeviceDetailsEntity;

@Repository
public interface DeviceDetRepository extends JpaRepository<DeviceDetailsEntity,Long >{

	@Query(value = " SELECT COUNT(*) FROM device_details WHERE is_active=true AND token= ?1 ",nativeQuery = true)
	Integer countByToken(String token);
	}
