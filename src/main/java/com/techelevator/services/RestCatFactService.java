package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatFact;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatFactService implements CatFactService {

	private RestTemplate restTemplate = new RestTemplate();
	private final String CAT_FACT_URL = "https://cat-data.netlify.app/api/facts/random";
	@Override
	public CatFact getFact() {
		// TODO Auto-generated method stub
		CatFact catFact = restTemplate.getForObject(CAT_FACT_URL, CatFact.class);
		return catFact;
	}

}
