package com.tripped.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "TRIP")
public class TripImpl implements Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CREATORID", nullable = false)
	private Long creatorid;

	@Column(name = "LOCATION", nullable = false)
	private String location;

	@Column(name = "DURATION", nullable = true)
	private String duration;

	@Column(name = "COST", nullable = true)
	private String cost;

	@Column(name = "DATE", nullable = true)
	private Date date;

	public TripImpl() {
		// Default Constructor
	}

	public TripImpl(Long creatorid, String location, String duration, String cost, Date date) {
		this.location = location;
		this.duration = duration;
		this.cost = cost;
		this.date = date;
	}

	public TripImpl(String location) {
		this.location = location;
	}

	public TripImpl(Trip trip) {
		BeanUtils.copyProperties(trip, this, TripImpl.class);
	}

	public Long getCreatorId() {
		return creatorid;
	}

	public void setCreatorId(Long id) {
		this.creatorid = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String toString() {
		return "TripImpl = " + this.getCreatorId() + " " + this.getLocation() + " " + this.getCost() + " "
				+ this.getDuration() + " " + this.getDate();
	}

	@Override // not used so removed and reorganize
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		return result;
	}

	@Override // Compare the actual data, overriding is overkill
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TripImpl other = (TripImpl) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}
}
