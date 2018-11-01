package com.apap.tutorial7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;

@Repository
public interface FlightDB extends JpaRepository<FlightModel, Long> {
	FlightModel findByFlightNumber(String flightNumber);
}
