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

import com.tripped.manager.TrippedManager;
import com.tripped.model.Trip;
import com.tripped.model.TripView;

@RequestMapping(value = "/trip/")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TripController {
	@Autowired
	private TrippedManager tripManager;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TripView>> listAllTrips() {
		List<TripView> trips = tripManager.findAllTrips();
		if (trips.isEmpty()) {
			return new ResponseEntity<List<TripView>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TripView>>(trips, HttpStatus.OK);
	}

	@RequestMapping(value = "userTrips/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<TripView>> listAllUserTrips(@PathVariable("id") Long id) {
		List<TripView> trips = tripManager.findAllUserTrips(id);
		if (trips.isEmpty()) {
			return new ResponseEntity<List<TripView>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TripView>>(trips, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Trip> getTrip(@PathVariable("id") Long id) {
		TripView trip = tripManager.findById(id);
		if (trip == null) {
			return new ResponseEntity<Trip>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Trip>(trip, HttpStatus.OK);
	}

	@RequestMapping(value = "addTrip", method = RequestMethod.POST)
	public ResponseEntity<Trip> createTrip(@RequestBody TripView trip) {
		if (trip == null) {
			return new ResponseEntity<Trip>(HttpStatus.NO_CONTENT);
		}
		System.out.println("In add TRIP");
		System.out.println("the trip that was sent" + trip);
		Trip returnedTrip = tripManager.saveTrip(trip);
		return new ResponseEntity<Trip>(returnedTrip, HttpStatus.CREATED);
	}

	@RequestMapping(value = "updateTrip/{id}", method = RequestMethod.PUT)
	public ResponseEntity<TripView> updateTrip(@PathVariable("id") Long id, @RequestBody TripView trip) {
		System.out.println("The update was hit");
		if (trip == null) {
			return new ResponseEntity<TripView>(HttpStatus.NO_CONTENT);
		}
		tripManager.updateTrip(trip);
		return new ResponseEntity<TripView>(trip, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Trip> deleteTrip(@PathVariable("id") Long id) {
		try {
			tripManager.deleteTripById(id);
			return new ResponseEntity<Trip>(HttpStatus.OK);
		} catch (Exception e) { // log the exception
			return new ResponseEntity<Trip>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "deleteUser/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Trip> deleteUserTrip(@PathVariable("id") Long id) {
		try {
			tripManager.deleteUserTrip(id);
			return new ResponseEntity<Trip>(HttpStatus.OK);
		} catch (Exception e) { // log the exception
			return new ResponseEntity<Trip>(HttpStatus.BAD_REQUEST);
		}

	}
}
