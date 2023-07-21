package vttp2023.batch3.assessment.paf.bookings.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ListingsRepository {
	private final String C_LISTINGS = "listings";
	private final String A_COUNTRY = "address.country";

	@Autowired
	MongoTemplate mongoTemplate;
	
	//TODO: Task 2

	// db.listings.distinct("address.country")
	public List<String> getCountries() {
		return mongoTemplate.findDistinct(new Query(), A_COUNTRY, C_LISTINGS, getClass(), String.class);
	}

	
	//TODO: Task 3


	//TODO: Task 4
	

	//TODO: Task 5


}
