package com.tripped.manager;

import java.util.List;

import com.tripped.model.TripImpl;
import com.tripped.model.TripView;

public interface TrippedManager {

	TripView findById(Long id);

	TripImpl saveTrip(TripView trip);

	List<TripView> findAllTrips();

	TripImpl updateTrip(TripView trip);

	void deleteTripById(Long id);

	void deleteUserTrip(Long id);

	List<TripView> copyTrips(List<TripImpl> trippedList);

	List<String> getTripNames(List<TripImpl> trippedList);

	List<TripView> findAllUserTrips(Long id);

}
