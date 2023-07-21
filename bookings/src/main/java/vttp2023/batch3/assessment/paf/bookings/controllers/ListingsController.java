package vttp2023.batch3.assessment.paf.bookings.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;
import vttp2023.batch3.assessment.paf.bookings.models.Search;

@Controller
public class ListingsController {

	//TODO: Task 2
	@GetMapping(path = "/")
	public String landingPage(Model model) {
		List<String> options = new LinkedList<>();
		options.add("a");options.add("b");options.add("c");
		model.addAttribute("options", options);
		model.addAttribute("search", new Search());

		return "view1";
	}

	@GetMapping(path = "/search")
	public String listingsPage(@Valid Search search, BindingResult bind, Model model) {

		if (bind.hasErrors()) {
            System.out.println("ERROR ERROR ERROR");
            System.out.println("Error count: " + bind.getErrorCount());
            return "view1";
        }

		return "view2";
	}

	
	//TODO: Task 3


	//TODO: Task 4
	

	//TODO: Task 5


}
