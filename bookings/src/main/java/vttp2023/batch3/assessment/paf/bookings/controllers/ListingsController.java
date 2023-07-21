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
import vttp2023.batch3.assessment.paf.bookings.models.Listing;
import vttp2023.batch3.assessment.paf.bookings.models.Param;
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

		// Param p1 = new Param(country);
		// Param p2 = new Param(Integer.toString(person));
		// Param p3 = new Param(Double.toString(priceMin));
		// Param p4 = new Param(Double.toString(priceMax));
		// model.addAttribute("p1", p1);
		// model.addAttribute("p2", p2);
		// model.addAttribute("p3", p3);
		// model.addAttribute("p4", p4);

		Search s = (Search) model.getAttribute("search");
		if(s.getPriceMax() < s.getPriceMin()) {
			bind.rejectValue("priceRange", "error.priceRange",
				"Max price must be greater than or equal to min price");
		}
		
		if (bind.hasErrors()) {
			model.addAttribute("options", listService.getCountries());
			// model.addAttribute("search", new Search());
            return "view1";
        }

		// System.out.println(listService.getPreviews(s.getCountry(), 
		// 	s.getPerson(), s.getPriceMin(), s.getPriceMax()).get(0).getImage());

		List<Listing> listings = listService.getPreviews(s.getCountry(), s.getPerson(), 
			s.getPriceMin(), s.getPriceMax());
		model.addAttribute("listings", listings);

		return "view2";
	}
	


	//TODO: Task 4
	@PostMapping(path = "/details/")
	public String getDetails(@RequestParam String id, Model model, Search search) {
		Listing details = listService.getListingById(id);
		model.addAttribute("details", details);

		// model.addAllAttribute("booking", new Booking());
		return "view3";
	}
	

	//TODO: Task 5


}
