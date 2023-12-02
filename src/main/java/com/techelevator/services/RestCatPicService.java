package com.techelevator.services;

import com.techelevator.model.CatFact;
import org.springframework.stereotype.Component;

import com.techelevator.model.CatPic;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatPicService implements CatPicService {
	private RestTemplate restTemplate = new RestTemplate();
	private final String CAT_PIC_URL = "https://cat-data.netlify.app/api/pictures/random";

	@Override
	public CatPic getPic() {
		// TODO Auto-generated method stub
		CatPic catPic = restTemplate.getForObject(CAT_PIC_URL, CatPic.class);
		return catPic;
	}

}	
