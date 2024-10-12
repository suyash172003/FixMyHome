package com.example.FixMyHome.Controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.FixMyHome.Model.OtpModel;
import com.example.FixMyHome.Model.RegisterModel;
import com.example.FixMyHome.Repository.RegisterRepository;
import com.example.FixMyHome.Service.EmailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RegisterController {
	
	@Autowired
	EmailService email;
	
	@Autowired
	RegisterRepository registering;
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("register", new RegisterModel());
		return "Register";
	}
	
	@PostMapping("/register")
	public String otpPage(Model model,@ModelAttribute("register") RegisterModel register,HttpSession session,RedirectAttributes redirect) {
		if(registering.getByEmail(register.getEmail())==null) {
			Random random=new Random(10001);
			int generate=random.nextInt(999999);
			session.setAttribute("register", register);
			email.sendEmailTo(register.getEmail(), "OTP :"+ generate);
			OtpModel otp=new OtpModel();
			otp.setGeneratedOTP(String.valueOf(generate));
			model.addAttribute("otp",otp);
			return "otpPage1";
		}
		redirect.addFlashAttribute("errorMessage", "Already Registered");
		return "redirect:/register";
	}
	
	@PostMapping("/otp1")
	public String otpPage(@ModelAttribute("otp") OtpModel otp,Model model,HttpSession session,RedirectAttributes redirect) {
		RegisterModel register=(RegisterModel) session.getAttribute("register");
		if(otp.getGeneratedOTP().compareTo(otp.getFormOTP())==0) {
			redirect.addFlashAttribute("message", "Registered Successfully!!");
			registering.save(register);
			return "redirect:/login";
		}
		redirect.addFlashAttribute("message", "OTP Verification failed!!");
		return "otpPage";
	}
}
