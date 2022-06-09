package com.example.YTlogin.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.YTlogin.Entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long >{
	
	UserEntity findByEmail(String email);
	
	@Query(value = "Select * from users where user_ref = ?1", nativeQuery = true)
	UserEntity findByRefCode(String code);

}
