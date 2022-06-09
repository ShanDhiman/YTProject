package com.example.YTlogin.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.YTlogin.Entity.LoginDetailsEntity;

@Repository
public interface LoginDetRepository extends JpaRepository<LoginDetailsEntity,Long> {

}
