package com.apap.tutorial7.controller;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.rest.PilotDetail;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.PilotService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/pilot")
public class PilotController {
	
	@Autowired
	private PilotService pilotService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}
	
	@GetMapping( value="/status/{licenseNumber}")
	public String getStatus(@PathVariable("licenseNumber") String licenseNumber) throws Exception{
		String path = Setting.pilotUrl + "/pilot?licenseNumber=" + licenseNumber;
		return restTemplate.getForEntity(path, String.class).getBody();
	}
		
	@GetMapping(value="/full/{licenseNumber}")
	public PilotDetail postStatus(@PathVariable("licenseNumber") String licenseNumber) throws Exception{
		String path = Setting.pilotUrl + "/pilot";
		PilotModel pilot =pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		PilotDetail detail = restTemplate.postForObject(path, pilot, PilotDetail.class);
		return detail;
	}
	
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	
	@PostMapping(value="/add")
	public PilotModel addPilotSubmit(@RequestBody PilotModel pilot) {
		return pilotService.addPilot(pilot);
	}
	
	@GetMapping(value = "/view/{licenseNumber}")
	public PilotModel pilotView(@PathVariable("licenseNumber") String licenseNumber) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		return pilot;
	}
	
	@DeleteMapping(value = "/delete")
	public String deletePilot(@RequestParam("pilotId") long pilotId) {
		PilotModel pilot = pilotService.getPilotDetailById(pilotId);
		pilotService.deletePilot(pilot);
		return "success";
	}
	
	@PutMapping(value= "/update/{pilotId}")
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
	/**@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot",new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	
	@RequestMapping(value="/pilot/update/{licenseNumber}", method = RequestMethod.POST)
	private String update(@PathVariable(value= "licenseNumber") String licenseNumber, @RequestParam(value="name") String name, @RequestParam(value="flyHour") Integer flyHour) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		pilotService.updatePilot(pilot, name, flyHour);
		
		return "updated";
	}
	
	@RequestMapping(value="/pilot/update/{licenseNumber}", method = RequestMethod.GET)
	private String updatePilotSubmit(@PathVariable(value= "licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilot", pilot);
		
		return "update-pilot";
	}
	
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	private String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilot", archive);
		
		List<FlightModel> archive2 = pilotService.getPilotDetailByLicenseNumber(licenseNumber).getPilotFlight();
		model.addAttribute("listFlight", archive2);
		
		return "view-pilot";
	}
	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
	private String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilot", pilot);
		model.addAttribute("listFlight", pilot.getPilotFlight());
		
		return "view-pilot";
	}
	
	
	@RequestMapping(value = "/pilot/delete/{licenseNumber}", method = RequestMethod.GET)
	private String delete(@PathVariable(value= "licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		pilotService.deletePilot(pilot);
		
		return "delete-pilot";
	}
	*/
}
