package vttp2023.batch3.assessment.paf.bookings.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import vttp2023.batch3.assessment.paf.bookings.models.Booking;
import vttp2023.batch3.assessment.paf.bookings.models.Listing;
import vttp2023.batch3.assessment.paf.bookings.models.Search;
import vttp2023.batch3.assessment.paf.bookings.services.ListingsService;

@Controller
public class ListingsController {

	@Autowired
	ListingsService listService;

	//TODO: Task 2
	@GetMapping(path = "/")
	public String landingPage(Model model) {
		model.addAttribute("options", listService.getCountries());
		model.addAttribute("search", new Search());

		return "view1";
	}

	
	//TODO: Task 3
	@GetMapping(path = "/search")
	public String listingsPage(@Valid Search search, BindingResult bind, Model model,
	@RequestParam String country, @RequestParam int person, @RequestParam double priceMin,
	@RequestParam double priceMax) {

		Search s = (Search) model.getAttribute("search");
		if(s.getPriceMax() < s.getPriceMin()) {
			bind.rejectValue("priceRange", "error.priceRange",
				"Max price must be greater than or equal to min price");
		}
		
		if (bind.hasErrors()) {
			model.addAttribute("options", listService.getCountries());
            return "view1";
        }

		List<Listing> listings = listService.getPreviews(s.getCountry(), s.getPerson(), 
			s.getPriceMin(), s.getPriceMax());

		if (listings.isEmpty()) {
			bind.rejectValue("priceRange", "error.priceRange",
				"No listings available. Try different search queries?");
			return "view1";
		}
		model.addAttribute("listings", listings);

		return "view2";
	}
	


	//TODO: Task 4
	@PostMapping(path = "/details/")
	public String getDetails(@RequestParam String id, Model model, Search search) {
		Listing details = listService.getListingById(id);

		if (details == null) {
			return "task4error";
		}

		model.addAttribute("search", search);
		model.addAttribute("details", details);

		Booking booking = new Booking();
		booking.setAccId(id);
		model.addAttribute("booking", booking);
		return "view3";
	}
	

	//TODO: Task 5
	@PostMapping(path = "/reserve")
	public String reserve(Booking booking, Model model) {
		Booking b = (Booking) model.getAttribute("booking");
		// System.out.println(b);
		// System.out.println(b.getAccId());
		
		listService.book(b);
		// System.out.println(b.getResvId());

		model.addAttribute("resvId", b.getResvId());
		
		return "view4";
	}

}
