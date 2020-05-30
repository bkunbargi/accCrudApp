package com.tripped.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tripped.model.TripCoords;
import com.tripped.service.CoordsServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/tripcoords")
@RestController
public class CoordController {
	@Autowired
	private CoordsServiceImpl coordsService;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<List<TripCoords>> listAllCoords(@PathVariable("id") Long id) {
		System.out.println("Attemping to get all coords");
		List<TripCoords> users = coordsService.findAllCoords(id);
		if (users.isEmpty()) {
			return new ResponseEntity<List<TripCoords>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TripCoords>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/addCoords", method = RequestMethod.POST, consumes = { "application/json" })
	public ResponseEntity<Void> createCoords(@RequestBody List<TripCoords> coords) {
		System.out.println("Coords to save: " + coords);
		if (coords == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		coordsService.saveCoords(coords);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/updateCoords", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateTrip(@RequestBody List<TripCoords> coords) {
		System.out.println("The update was hit");
		if (coords == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		coordsService.updateTrip(coords);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

//	@RequestMapping(value = "{id}", method = RequestMethod.GET)
//	public ResponseEntity<String> returnUserName(@PathVariable("id") Long id) {
//		System.out.println("Different coords one");
////		TripCoords person = coordsService.findById(id);
////		System.out.println(person.getName());
//		return new ResponseEntity<String>(HttpStatus.OK);
//	}

}
