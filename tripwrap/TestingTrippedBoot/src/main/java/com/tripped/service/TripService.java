package com.tripped.service;

import java.util.List;

import com.tripped.model.TripImpl;

public interface TripService {

	TripImpl findById(Long id);

	TripImpl saveTrip(TripImpl trip);

	List<TripImpl> findAllTrips();

	void deleteTripById(Long id);

	List<TripImpl> findAllUserTrips(Long id);

	void deleteUserTrip(Long id);

}
