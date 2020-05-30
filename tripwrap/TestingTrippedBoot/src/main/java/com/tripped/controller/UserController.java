package com.tripped.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tripped.model.UserImpl;
import com.tripped.service.UserServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/user")
@RestController
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserImpl>> listAllUsers() {
		List<UserImpl> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserImpl>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserImpl>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public ResponseEntity<Long> getUserEmail(@RequestBody UserImpl user) {
//		System.out.println("User we got" + user);
		user = userService.findByEmailAddress(user.getEmail(), user.getPassword());
//		System.out.println(user.getId());
		if (user == null) {
			return new ResponseEntity<Long>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Long>(user.getId(), HttpStatus.OK);
	}

	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody UserImpl user) {
		if (user == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		userService.saveUser(user);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

//	@RequestMapping(value = "{id}", method = RequestMethod.GET)
//	public ResponseEntity<UserImpl> getUser(@PathVariable("id") Long id) {
//		UserImpl user = userService.findById(id);
//		if (user == null) {
//			return new ResponseEntity<UserImpl>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<UserImpl>(user, HttpStatus.OK);
//	}
}
