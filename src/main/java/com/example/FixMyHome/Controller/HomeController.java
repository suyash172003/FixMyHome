package com.example.FixMyHome.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.FixMyHome.Model.LoginModel;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String homePage(@ModelAttribute("login")LoginModel login,HttpSession session,Model model) {
		LoginModel login1=(LoginModel) session.getAttribute("login");
		if(login1==null) {
			model.addAttribute("login",null);
		}
		return "Home";
	}

}
