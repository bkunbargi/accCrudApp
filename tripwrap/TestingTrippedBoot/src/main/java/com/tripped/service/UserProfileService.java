package com.tripped.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripped.model.UserProfile;
import com.tripped.repo.UserProfileRepo;

@Service
public class UserProfileService {

	@Autowired
	private UserProfileRepo profileRepo;

	public List<UserProfile> findAllUsers() {
		return profileRepo.findAll();
	}

	public UserProfile findById(Long id) {
		return profileRepo.findById(id).get();
	}

}
