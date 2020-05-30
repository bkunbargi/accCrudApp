package com.tripped.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripped.model.UserImpl;
import com.tripped.repo.UserRepo;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepo userRepo;

//	public UserImpl findById(Long id) {
//		return userRepo.findById(id).get();
//	}

	public UserImpl findByEmailAddress(String email, String password) {
		System.out.println("email and password being sent: " + email + " " + password);
		return userRepo.findByEmailAddress(email, password);
	}

	public List<UserImpl> findAllUsers() {
		return userRepo.findAll();
	}

	public UserImpl saveUser(UserImpl user) {
		return userRepo.save(user);
	}

//	public void deleteUserById(Long id) {
//		userRepo.deleteById(id);
//	}
}
