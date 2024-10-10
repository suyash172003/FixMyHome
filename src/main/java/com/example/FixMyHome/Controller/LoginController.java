package com.example.FixMyHome.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.FixMyHome.Model.LoginModel;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("login", new LoginModel());
		return "Login";
	}
	
	@PostMapping("/login")
	public String loginPage(@ModelAttribute("login") LoginModel login) {
		return "redirect:/login";
	}

	
}
