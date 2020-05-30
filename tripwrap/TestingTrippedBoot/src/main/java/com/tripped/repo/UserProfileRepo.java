package com.tripped.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tripped.model.UserProfile;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {

}
