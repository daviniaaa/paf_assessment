package vttp2023.batch3.assessment.paf.bookings.services;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2023.batch3.assessment.paf.bookings.models.Booking;
import vttp2023.batch3.assessment.paf.bookings.models.Listing;
import vttp2023.batch3.assessment.paf.bookings.repositories.BookingRepository;
import vttp2023.batch3.assessment.paf.bookings.repositories.ListingsRepository;
import vttp2023.batch3.assessment.paf.bookings.utility.BookingsUtility;

@Service
public class ListingsService {


	@Autowired
	ListingsRepository repo;

	@Autowired
	BookingRepository bookRepo;

	@Autowired
	BookingsUtility bookUtil;
	
	//TODO: Task 2
	public List<String> getCountries() {
		return repo.getCountries();
	}
	
	//TODO: Task 3
	public List<Listing> getPreviews(String country, int person, double priceMin, double priceMax) {

		return repo.getPreviews(country, person, priceMin, priceMax);
	}


	//TODO: Task 4
	public Listing getListingById(String id) {
		
		return repo.getListingById(id);
	}
	

	//TODO: Task 5

	@Transactional
	public void book(Booking booking) {
		String accId = booking.getAccId();
		int vacancy = bookRepo.getVacancyById(accId);
		int days = booking.getDuration();

		if ( days > vacancy ) {
			throw new IllegalArgumentException("No vacancy");
		}

		String resvId = bookUtil.generateUUID(8);
		booking.setResvId(resvId);

		bookRepo.addBooking(booking, accId);

		bookRepo.updateVacancy(days, accId);
	}
	


}
