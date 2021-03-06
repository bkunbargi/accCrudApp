package com.tripped.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tripped.model.TripCoords;

public interface CoordRepo extends JpaRepository<TripCoords, Long> {
	@Query(value = "SELECT * from tripcoords t where t.tripid = :id", nativeQuery = true)
	List<TripCoords> findAll(@Param("id") Long id);

}

//package com.tripped.repo;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.tripped.model.TripImpl;
//
//public interface TrippedRepo extends JpaRepository<TripImpl, Long> {
//	@Query(value = "SELECT * from trip t WHERE t.creatorid = :id", nativeQuery = true)
//	List<TripImpl> findAllUserTrips(@Param("id") Long id);
//
//	@Query(value = "DELETE FROM trip t WHERE t.creatorid = :id", nativeQuery = true)
//	void deleteUserTrip(@Param("id") Long id);
//
//}
