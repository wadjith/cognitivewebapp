package com.cognitivefarms.webapp.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cognitivefarms.webapp.domaine.dto.AezClusterDto;
import com.cognitivefarms.webapp.domaine.dto.GpsPoint;
import com.cognitivefarms.webapp.domaine.entities.Farmer;
import com.cognitivefarms.webapp.domaine.entities.TestSet;
import com.cognitivefarms.webapp.domaine.qto.AezClusterQto;
import com.cognitivefarms.webapp.domaine.qto.ClusterQto;
import com.cognitivefarms.webapp.service.ICognitiveService;

/**
 * @author thierry WADJI
 *
 */
@Controller
@SessionAttributes(value = {"gpsPoint", "cultures"}, types = {GpsPoint.class, ArrayList.class})
public class AppController {
	
	private final ICognitiveService service;
	
	@Autowired	
	public AppController(ICognitiveService service) {
		super();
		this.service = service;
	}

	@GetMapping("/")
	public String home(Model model) {
		
		model.addAttribute("gpsPoint", new GpsPoint(0, 0));
		return "home";
	}
	
	@GetMapping("/location")
	public String locationInfo(Model model) {
		
		return "location";
	}
	
	@PostMapping("/location")
	public String locationInfo(GpsPoint point, Model model) {
		
		//Get the ph of soil
		double ph = service.getPH(point.getLat(), point.getLng());
		String phString = service.getPH(ph);
		
		//Weather and climate
		AezClusterQto aezQto = service.getWeather(point.getLat(), point.getLng());
		String weather = (aezQto.getAezText().trim().split("/")[0].contains("-")) ? aezQto.getAezText().trim().split("/")[0].split("-")[1] : aezQto.getAezText().trim().split("/")[0];
		String climate = aezQto.getAezText().trim().split("/")[1];
		AezClusterDto aezDto = new AezClusterDto(aezQto.getSubNatUnit(), climate.trim(), weather.trim());
		
		//List of crops
		ClusterQto clQto = service.getCluster(point.getLat(), point.getLng());
		List<String> cropList = new ArrayList<String>();
		cropList =	service.getCropPropertyofCrops(Arrays.asList(clQto.getCultures().split("/"))); 
		/*
		 * ClusterDto clDto = new ClusterDto(clQto.getSubNatUnit(), 
											Arrays.asList(clQto.getCultures().split("/")));	*/
		
		// Save list of culture in a session attribute
		// request.getSession().setAttribute("cultures", cropList);
		
		model.addAttribute("gpsPoint", point);
		model.addAttribute("ph", ph);
		model.addAttribute("phtext", phString);
		model.addAttribute("aez", aezDto);
		model.addAttribute("cultures", cropList);
		
		return "location";
	}
	
	/**
	 * Make the test set at the position and persist in database
	 * 
	 * @param request
	 * @param phtext
	 * @param weather
	 * @param climate
	 * @return
	 */
	@PostMapping("/confirm")
	public String confirmPosition(HttpServletRequest request,
									@RequestParam("phtext") String phtext, 
									@RequestParam("weather") String weather,
									@RequestParam("climate") String climate, 
									Model model) {
		
		List<TestSet> testSetList = new ArrayList<TestSet>();
		String strPoint = ((GpsPoint)request.getSession().getAttribute("gpsPoint")).toString();
		
		// By default i search a farmer for which id = 1
		Farmer farmer = service.searchFarmer(Long.valueOf(1L)); 
		
		//Construct a List of testSet object 
		for(String crop : (ArrayList<String>)request.getSession().getAttribute("cultures")) {
			TestSet test = new TestSet(crop, weather, phtext, climate, strPoint, null);
			test.setFarmer(farmer);
			testSetList.add(test);
		}
		// Persist testSet in database
		service.saveAllTestSet(testSetList);
		
		//add the string representation of point in ModelAttribute
		model.addAttribute("point", strPoint);
		
		return "confirm_prediction.html";
	}

}
