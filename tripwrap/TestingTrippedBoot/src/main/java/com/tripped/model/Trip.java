package com.tripped.model;

import java.util.Date;

public interface Trip {

	Long getId();

	void setId(Long id);

	String getLocation();

	void setLocation(String location);

	String getDuration();

	void setDuration(String duration);

	String getCost();

	void setCost(String cost);

	Date getDate();

	void setDate(Date date);
}
