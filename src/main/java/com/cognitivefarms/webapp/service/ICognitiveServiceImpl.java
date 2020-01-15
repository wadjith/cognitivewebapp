package com.cognitivefarms.webapp.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognitivefarms.webapp.domaine.entities.Crops;
import com.cognitivefarms.webapp.domaine.entities.Farmer;
import com.cognitivefarms.webapp.domaine.entities.TestSet;
import com.cognitivefarms.webapp.domaine.qto.AezClusterQto;
import com.cognitivefarms.webapp.domaine.qto.ClusterQto;
import com.cognitivefarms.webapp.repository.AezClusterRepository;
import com.cognitivefarms.webapp.repository.ClusterRepository;
import com.cognitivefarms.webapp.repository.CropsRepository;
import com.cognitivefarms.webapp.repository.FarmerRepository;
import com.cognitivefarms.webapp.repository.RasterRepository;
import com.cognitivefarms.webapp.repository.TestSetRepository;

@Service
public class ICognitiveServiceImpl implements ICognitiveService {
	
	private final AezClusterRepository aezRepo;
	private final ClusterRepository clusRepo;
	private final RasterRepository rastRepo;
	private final CropsRepository cropsRepo;
	private final TestSetRepository testRepo;
	private final FarmerRepository faRepo;
	
	@Autowired	
	public ICognitiveServiceImpl(AezClusterRepository aezRepo, ClusterRepository clusRepo, 
									RasterRepository rastRepo, CropsRepository cropsRepo, 
									FarmerRepository faRepo, TestSetRepository testRepo) 
	{
		super();
		this.aezRepo = aezRepo;
		this.clusRepo = clusRepo;
		this.rastRepo = rastRepo;
		this.cropsRepo = cropsRepo;
		this.testRepo = testRepo;
		this.faRepo = faRepo;
	}

	@Override
	public double getPH(double lat, double lng) {
		// TODO Auto-generated method stub
		return rastRepo.getPh(lat, lng);
	}

	@Override
	public List<Crops> getCrops(double lat, double lng) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Crops> getCrops(String group) {
		// TODO Auto-generated method stub
		return cropsRepo.findByGroupCode(group);
	}

	@Override
	public String getPH(double ph) {
		// TODO Auto-generated method stub
		String phInLetter = null;
		
		if (ph <= 50d) phInLetter = "Extrem Acid";
		else if (ph < 66d) phInLetter = "Acid";
		else if (ph < 74d) phInLetter = "Neutral";
		else if (ph < 85d) phInLetter = "Alkaline";
		else if (ph < 140d) phInLetter = "Strongly Alkaline";
		else phInLetter = "Not for culture";
		
		return phInLetter;
	}

	@Override
	public AezClusterQto getWeather(double lat, double lng) {
		
		String position = "POINT(" + lat + " " + lng + ")";
		List<AezClusterQto> qtos = new ArrayList<AezClusterQto>();
		qtos = aezRepo.findAtPosition(position);
		//TODO Some computations in order to get the best value to return  
		
		return qtos.get(0); //Return the first element of the list by default
	}

	@Override
	public ClusterQto getCluster(double lat, double lng) {
		
		String position = "POINT(" + lat + " " + lng + ")";
		ClusterQto qtoRep = null;
		List<ClusterQto> qtos = new ArrayList<ClusterQto>();
		qtos = clusRepo.findAtPosition(position);
		//TODO Some computations in order to get the best value to return  
		
		//Check the first cluster with cultures not empty
		for(ClusterQto qto : qtos) {
			if(qto.getCultures() == null) continue;
			else {
				qtoRep = qto;
				break;
			}
		}
		return qtoRep;
	}

	@Override
	public List<Crops> getCrops(List<String> groupList) {
		// TODO Auto-generated method stub
		return cropsRepo.findCropByGroupList(groupList);
	}

	@Override
	public List<String> getCropPropertyofCrops(List<String> groupList) {
		// TODO Auto-generated method stub
		return cropsRepo.getCropPropertyFromGroupList(groupList);
	}

	@Override
	public boolean saveTestSet(TestSet test) {
		// TODO Auto-generated method stub
		return (testRepo.save(test) != null) ? true : false;
	}

	@Override
	public boolean saveAllTestSet(List<TestSet> tests) {
		// TODO Auto-generated method stub
		return (testRepo.saveAll(tests).size() == tests.size()) ? true : false;
	}

	@Override
	public Farmer saveFarmer(Farmer farmer) {
		// TODO Auto-generated method stub
		return faRepo.save(farmer);
	}

	@Override
	public Farmer searchFarmer(Long id) {
		// TODO Auto-generated method stub
		return faRepo.findById(id).get();
	}

	@Override
	public Farmer searchByEmail(String email) {
		// TODO Auto-generated method stub
		return faRepo.findByEmail(email);
	}

	@Override
	public Farmer searchByTelephone(int phoneNumber) {
		// TODO Auto-generated method stub
		return faRepo.findByTelephone(phoneNumber);
	}
	
	

}
