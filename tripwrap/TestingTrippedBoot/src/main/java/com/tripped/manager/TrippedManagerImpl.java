package com.tripped.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger; //use log4j

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripped.model.Trip;
import com.tripped.model.TripImpl;
import com.tripped.model.TripView;
import com.tripped.service.TripService;

@Service
public class TrippedManagerImpl implements TrippedManager {
	@Autowired
	private TripService tripService;

	private Logger logger = Logger.getLogger(this.getClass().getName()); // missing private access modifier

	public TripView findById(Long id) {
		return new TripView(tripService.findById(id));
	}

	public List<TripView> findAllTrips() {
		List<TripView> tripList = new ArrayList<>();
		tripList = copyTrips(tripService.findAllTrips());
		logger.info("Sucessfully retrieved trips");
		return tripList;
	}

	public List<TripView> findAllUserTrips(Long id) {
		List<TripView> tripList = new ArrayList<>();
		tripList = copyTrips(tripService.findAllUserTrips(id));
		logger.info("Sucessfully retrieved trips");
		return tripList;
	}

	public TripImpl saveTrip(TripView trip) {
		System.out.println("In add trip manager");
		TripImpl tripImpl = new TripImpl(trip);
		System.out.println("Trip converted to IMPL: " + trip);
		if (getTripNames(tripService.findAllTrips()).contains(trip.getLocation())) {
			return new TripImpl();
		} else {
			tripImpl = tripService.saveTrip(tripImpl);
		}
		System.out.println("leaving add trip Manager");
		logger.info("Sucessfully saved trip: " + trip);
		return tripImpl;
	}

	public TripImpl updateTrip(TripView trip) {
		TripImpl newTrip = new TripImpl(trip);
		tripService.saveTrip(newTrip);
		logger.info("Succesfully updated trip: " + newTrip);
		return newTrip;
	}

	public void deleteTripById(Long id) {
		Long numDeleted = new Long(0);
		tripService.deleteTripById(id);
		logger.info("Number of trips deleted: " + numDeleted);
	}

	public void deleteUserTrip(Long id) {
		tripService.deleteUserTrip(id);
	}

	public List<TripView> copyTrips(List<TripImpl> list) { // chnage to private
		List<TripView> tripList = new ArrayList<>();
		for (Trip trip : list) {
			TripView modifiedTrip = new TripView(trip);
			tripList.add(modifiedTrip);
		}
		return tripList;
	}

	public List<String> getTripNames(List<TripImpl> trippedList) {
		List<String> tripNames = new ArrayList<>();
		for (Trip trip : trippedList) {
			tripNames.add(trip.getLocation());
		}
		return tripNames;
	}

}
