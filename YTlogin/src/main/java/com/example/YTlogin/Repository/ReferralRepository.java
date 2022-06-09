package com.example.YTlogin.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.YTlogin.Entity.ReferralEntity;

@Repository
public interface ReferralRepository extends JpaRepository<ReferralEntity,Long > {

    ReferralEntity findByReferralEmail(String email);
    
}
