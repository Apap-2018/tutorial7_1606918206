package com.apap.tutorial7.controller;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.service.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flight")
public class FlightController {
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	private List<FlightModel> arcFlight;
	
	@PostMapping(value="/add")
	public FlightModel addFlightSubmit(@RequestBody FlightModel flight) {
		return flightService.addFlight(flight);
	}
	
	@GetMapping(value = "/view/{flightNumber}")
	public FlightModel flightView(@PathVariable("flightNumber") String flightNumber) {
		FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
		return flight;
	}
	
	@GetMapping(value = "/all")
	public List<FlightModel> viewAll() {
		List<FlightModel> flight = flightService.getAllFlight();
		return flight;
	}
	
	@DeleteMapping(value = "/delete")
	public String deleteFlight(@RequestParam("flightId") long flightId) {
		FlightModel flight = flightService.getFlightDetailById(flightId);
		flightService.deleteFlight(flight);
		return "success";
	}
	
	@PutMapping(value= "/update/{flightId}")
	public String updatePilotSubmit(@PathVariable("pilotId") long pilotId,
			@RequestParam("name") String name,
			@RequestParam("flyHour") int flyHour) {
		PilotModel pilot = pilotService.getPilotDetailById(pilotId);
		if(pilot.equals(null)) {
			return "Couldn't find your pilot";
		}
		pilot.setName(name);
		pilot.setFlyHour(flyHour);
		pilotService.updatePilot(pilot, name, flyHour);
		return "update";
	}
	
	/**@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		arcFlight = pilot.getPilotFlight();
		pilot.setPilotFlight(new ArrayList<FlightModel>());
		pilot.addPilotFlight(flight);
				
		model.addAttribute("pilot", pilot);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.POST, params= {"save"})
	private String addFlightSubmit(@ModelAttribute PilotModel pilot) {
		for(FlightModel flight: arcFlight) {
			pilot.addPilotFlight(flight);
		}
		for(FlightModel flight : pilot.getPilotFlight()) {
			flight.setPilot(pilot);
			flightService.addFlight(flight);
		}
		return "add";
}
	@RequestMapping(value = "/flight/view", method = RequestMethod.GET)
	private String view(@RequestParam("flightNumber") String flightNumber, Model model) {
		FlightModel archive = flightService.getFlightDetailByFlightNumber(flightNumber);
		PilotModel archiveP = archive.getPilot();
		model.addAttribute("flight", archive);
		model.addAttribute("pilot", archiveP);
		
		
		return "view-flight";
	}
	
	@RequestMapping(value="/flight/update/{flightNumber}", method = RequestMethod.POST)
	private String update(@PathVariable(value="flightNumber") String flightNumber, @RequestParam(value="origin") String origin, @RequestParam(value="destination") String destination, @RequestParam(value="time") Date time) {
		FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
		flightService.updateFlight(flight, origin, destination, time);
		
		return "add";	
	}
	
	@RequestMapping(value="/flight/update/{flightNumber}", method = RequestMethod.GET)
	private String updatePilotSubmit(@PathVariable(value= "flightNumber") String flightNumber, Model model) {
		FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
		model.addAttribute("flight", flight);
		
		return "update-flight";
	}

	
	@RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
	private String delete(@ModelAttribute PilotModel pilot, Model model) { 
		for(FlightModel flight :pilot.getPilotFlight()) {
			flightService.deleteFlightById(flight.getId());
		}
		return "delete-pilot";
	}
	
	@RequestMapping(value="/flight/add/{licenseNumber}", params={"addRow"}, method = RequestMethod.POST)
	public String addRow(@ModelAttribute PilotModel pilot, BindingResult bindingResult, Model model) {
		pilot.getPilotFlight().add(new FlightModel());
	    model.addAttribute("pilot", pilot);
	    return "addFlight";
	}	
	
	@RequestMapping(value="/flight/add/{licenseNumber}", method = RequestMethod.POST, params={"removeRow"})
	public String removeRow(@ModelAttribute PilotModel pilot, final BindingResult bindingResult, final HttpServletRequest req, Model model) {
	    final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
	    pilot.getPilotFlight().remove(rowId.intValue());
	    
	    model.addAttribute("pilot", pilot);
	    return "addFlight";
	}
	
	
	
	@RequestMapping(value = "/flight/delete/{flightNumber}", method = RequestMethod.GET)
	private String delete(@PathVariable(value= "flightNumber") String flightNumber, Model model) {
		FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
		flightService.deleteFlight(flight);
		
		return "delete-pilot";
	}*/

	
	

}
