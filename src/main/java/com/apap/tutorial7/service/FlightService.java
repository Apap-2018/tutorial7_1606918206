package com.apap.tutorial7.service;

import java.sql.Date;
import java.util.List;

import com.apap.tutorial7.model.FlightModel;

public interface FlightService {
	FlightModel addFlight(FlightModel flight);
	public FlightModel getFlightDetailByFlightNumber(String flightNumber);
	public void updateFlight(FlightModel flight, String Origin, String Destination, Date date);
	void deleteFlight(FlightModel flight);
	void deleteFlightById(long id);
	FlightModel getFlightDetailById(long flightId);
	List<FlightModel> getAllFlight();
}
