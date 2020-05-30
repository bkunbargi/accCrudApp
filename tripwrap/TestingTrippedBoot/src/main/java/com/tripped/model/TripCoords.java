package com.tripped.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRIPCOORDS")
public class TripCoords {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tripid", nullable = false)
	private Long tripId;

	@Override
	public String toString() {
		return "TripCoords [id=" + id + ", tripId=" + tripId + ", poi=" + poi + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}

	@Column(name = "poi")
	private String poi;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;

	@Column(name = "note")
	private String note;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public TripCoords() {
		// Default Constructor
	}

	public TripCoords(Long tripId, String latitude, String longitude, String poi, String note) {
		this.tripId = tripId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.poi = poi;
		this.note = note;
	}

	public String getPoi() {
		return poi;
	}

	public void setPoi(String poi) {
		this.poi = poi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTripId() {
		return tripId;
	}

	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}

	public String getlatitude() {
		return latitude;
	}

	public void setlatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getlongitude() {
		return longitude;
	}

	public void setlongitude(String longitude) {
		this.longitude = longitude;
	}
}
