package com.cognitivefarms.webapp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognitivefarms.webapp.CognitiveFarmsWebappApplication;
import com.cognitivefarms.webapp.CognitiveFarmsWebappApplicationTests;
import com.cognitivefarms.webapp.domaine.entities.AezCluster;
import com.cognitivefarms.webapp.domaine.qto.AezClusterQto;

/**
 * Test of AezClusterRepository class
 * 
 * @author thierry WADJI
 *
 */

public class AezClusterRepositoryIntegrationTest extends CognitiveFarmsWebappApplicationTests{
	
	@Autowired
	private AezClusterRepository aezRepo;
	
	//Writing test cases
	@Test
	public void whenFindAtPosition_thenReturnListAezClusterQto() {
		//Given the position
		double lat = -1.32;
		double lng = 26.65;
		String position = "POSITION("+ lng + " " + lat +")";
		
		//When
		List<AezClusterQto> qtoList = aezRepo.findAtPosition(position);
		
		//Then
		assertThat(qtoList.size()).isEqualTo(10); // 
		
		
	}

}
