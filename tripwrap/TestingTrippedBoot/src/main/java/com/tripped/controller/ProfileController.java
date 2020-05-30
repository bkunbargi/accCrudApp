package com.tripped.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tripped.model.UserProfile;
import com.tripped.service.UserProfileService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/profile")
@RestController
public class ProfileController {

	@Autowired
	private UserProfileService profileService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserProfile>> listAllUsers() {
		List<UserProfile> users = profileService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserProfile>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserProfile>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<String> returnUserName(@PathVariable("id") Long id) {
		System.out.println("END POINT HIT");
		UserProfile person = profileService.findById(id);
		System.out.println(person.getName());
		return new ResponseEntity<String>(person.getName(), HttpStatus.OK);
	}

}
