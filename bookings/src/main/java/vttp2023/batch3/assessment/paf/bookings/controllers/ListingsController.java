package vttp2023.batch3.assessment.paf.bookings.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;
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

	@GetMapping(path = "/search")
	public String listingsPage(@Valid Search search, BindingResult bind, Model model) {
		Search s = (Search) model.getAttribute("search");
		if(s.getPriceMax() < s.getPriceMin()) {
			bind.rejectValue("priceRange", "error.priceRange",
				"Max price must be greater than or equal to min price");
		}
		
		if (bind.hasErrors()) {
            System.out.println("ERROR ERROR ERROR");
            System.out.println("Error count: " + bind.getErrorCount());
			model.addAttribute("options", listService.getCountries());
			// model.addAttribute("search", new Search());
            return "view1";
        }

		return "view2";
	}

	
	//TODO: Task 3


	//TODO: Task 4
	

	//TODO: Task 5


}
