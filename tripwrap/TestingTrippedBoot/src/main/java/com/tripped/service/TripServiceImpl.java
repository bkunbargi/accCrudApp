package com.tripped.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripped.model.TripImpl;
import com.tripped.repo.TrippedRepo;

@Service
public class TripServiceImpl implements TripService {

	@Autowired
	private TrippedRepo trippedRepo;

	public TripImpl findById(Long id) {
		return trippedRepo.findById(id).get();
	}

	public List<TripImpl> findAllTrips() {
		return trippedRepo.findAll();
	}

	public List<TripImpl> findAllUserTrips(Long id) {
		return trippedRepo.findAllUserTrips(id);
	}

	public TripImpl saveTrip(TripImpl trip) {
		return trippedRepo.save(trip);
	}

	public void deleteTripById(Long id) {
		trippedRepo.deleteById(id);
	}

	public void deleteUserTrip(Long id) {
		trippedRepo.deleteUserTrip(id);
	}
}
