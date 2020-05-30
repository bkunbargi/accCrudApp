package com.tripped.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripped.model.TripCoords;
import com.tripped.repo.CoordRepo;

@Service
public class CoordsServiceImpl {

	@Autowired
	private CoordRepo coordRepo;

	public List<TripCoords> findAllCoords(Long id) {
		System.out.println("Find all coords service");
//		System.out.println(coordRepo.findAll());
		for (TripCoords temp : coordRepo.findAll(id)) {
			System.out.println(temp.toString());
			System.out.println("Trip ID: " + temp.getTripId());
			System.out.println("ID: " + temp.getId());
		}
		return coordRepo.findAll(id);
	}

	public void saveCoords(List<TripCoords> coords) {
		String queryString = "VALUES(";
		for (TripCoords coord : coords) {
			System.out.println(coord);
			coordRepo.save(coord);
		}
//		"INSERT INTO tripcoords(tripId, poi, lat, lng) VALUES(:tripid, :poi, :lat, :lng)
//		("36", "China", "33.957947", "109.975585"),
//		("36", "China", "37.801973", "92.419459");
//		coordRepo.save(queryString);
	}

	public TripCoords findById(Long id) {
		return coordRepo.findById(id).get();
	}

}
