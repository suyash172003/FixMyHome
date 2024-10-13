package com.example.FixMyHome.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ServicesController {
	
	@GetMapping("/services")
	public String servicePage() {
		return "ServicesInfo";
	}
	
	@GetMapping("/book-service")
	public String bookingPage(@RequestParam("serviceName") String serviceName,Model model) {
		model.addAttribute("serviceName", serviceName);
		return "BookingModel";
	}	
}
