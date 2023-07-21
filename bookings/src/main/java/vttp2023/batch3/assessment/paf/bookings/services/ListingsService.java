package vttp2023.batch3.assessment.paf.bookings.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch3.assessment.paf.bookings.models.Listing;
import vttp2023.batch3.assessment.paf.bookings.repositories.ListingsRepository;

@Service
public class ListingsService {


	@Autowired
	ListingsRepository repo;
	
	//TODO: Task 2
	public List<String> getCountries() {
		return repo.getCountries();
	}
	
	//TODO: Task 3
	public List<Listing> getPreviews(String country, int person, double priceMin, double priceMax) {

		return repo.getPreviews(country, person, priceMin, priceMax);
	}


	//TODO: Task 4
	

	//TODO: Task 5


}
