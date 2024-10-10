package com.example.FixMyHome.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.FixMyHome.Model.RegisterModel;

@Controller
public class RegisterController {
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("register", new RegisterModel());
		return "Register";
	}
	
	@PostMapping("/register")
	public String registerPage(@ModelAttribute("register") RegisterModel register) {
		return "redirect:/login";
	}

}
