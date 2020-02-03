package com.cognitivefarms.webapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.support.SessionStatus;

import com.cognitivefarms.webapp.domaine.dto.Classification;
import com.cognitivefarms.webapp.domaine.dto.ClusterDto;
import com.cognitivefarms.webapp.service.DataSetFactory;
import com.cognitivefarms.webapp.service.Titanic;

import weka.core.Instances;

@Controller
@RequestMapping("/prediction")
public class PredictionController {
	
	private final DataSetFactory dataSetFacory;
	
	@Autowired
	public PredictionController(DataSetFactory datasetFacory) {
		super();
		this.dataSetFacory = datasetFacory;
	}
	

	/**
	 * 
	 * Make a prediction and delete session variables
	 * @param point
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String prediction(@RequestParam("point") String point, 
								SessionStatus status,
								Model model) throws Exception {
		
		final long time = System.currentTimeMillis();
		
		Instances trainSetData, testSetData;
		trainSetData = dataSetFacory.getTrainingData();
		testSetData = dataSetFacory.makeTestSet(point);
		Titanic tt = new Titanic(trainSetData, testSetData);
		tt.train();
		final List<Classification> classList = tt.classify();
		
		final long duree = (System.currentTimeMillis() - time)/1000;
		
		model.addAttribute("duree", duree);
		model.addAttribute("classList", classList);
		
		//Destroy sessions variables
		status.setComplete();
		
		return "prediction";
	}

}
