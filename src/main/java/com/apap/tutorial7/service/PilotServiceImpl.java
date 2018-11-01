package com.apap.tutorial7.service;

import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.repository.PilotDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PilotServiceImpl implements PilotService{
	
	@Autowired
	private PilotDB pilotDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}
	
	@Override
	public PilotModel addPilot(PilotModel pilot) {
		return pilotDb.save(pilot);	
	}
	
	public void deletePilot(PilotModel pilot) {
		pilotDb.delete(pilot);
	}
	
	@Override
	public void updatePilot(PilotModel pilot,String name, Integer flyHour) {
		pilot.setName(name);
		pilot.setFlyHour(flyHour);
		pilotDb.save(pilot);
	}

	@Override
	public PilotModel getPilotDetailById(long pilotId) {
		// TODO Auto-generated method stub
		return pilotDb.findById(pilotId).get();
	}
	
	
}
