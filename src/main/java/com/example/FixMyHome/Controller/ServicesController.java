package com.example.FixMyHome.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.FixMyHome.Model.BookedModel;
import com.example.FixMyHome.Repository.BookingRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ServicesController {
	
	@Autowired
	BookingRepository book;
	
	@GetMapping("/services")
	public String servicePage() {
		return "ServicesInfo";
	}
	
	@GetMapping("/book-service")
	public String bookingPage(@RequestParam("serviceName") String serviceName,Model model,HttpSession session) {
		model.addAttribute("serviceName", serviceName.trim());
		model.addAttribute("booking", new BookedModel());
		return "BookingModel";
	}
	
	@PostMapping("/book-service")
	public String bookedServicePage(@ModelAttribute("booking") BookedModel booking,@RequestParam("serviceName")String serviceName,RedirectAttributes redirect,HttpSession session) {
		booking.setStatus("IN PROGRESS");
		booking.setUserId((Long) session.getAttribute("userId"));
		book.save(booking);
		redirect.addFlashAttribute("message", "Booking is placed Successfully");
		return "redirect:/book-service?serviceName="+serviceName.trim();
	}
	
	@GetMapping("/bookings")
	public String booked(Model model,@ModelAttribute("booking") BookedModel booking,HttpSession session) {
		
		List<BookedModel> list=book.findAllByUserIdOrderByBookingDateAsc((Long) session.getAttribute("userId"));
		model.addAttribute("Booked", list);
		return "Bookings";
	}
}
