package com.example.FixMyHome.Controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.FixMyHome.Model.LoginModel;
import com.example.FixMyHome.Model.OtpModel;
import com.example.FixMyHome.Model.RegisterModel;
import com.example.FixMyHome.Repository.RegisterRepository;
import com.example.FixMyHome.Service.EmailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	Random random=new Random(10001);
	
	@Autowired
	EmailService email;
	
	@Autowired
	RegisterRepository registering;
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("login", new LoginModel());
		return "Login";
	}
	
	@PostMapping("/login")
	public String loginPage(@ModelAttribute("login") LoginModel login,RedirectAttributes redirect,HttpSession session) {
		String email=login.getEmail();
		if(registering.getByEmail(email)!=null && registering.getByEmail(email).getPassword().compareTo(login.getPassword())==0) {
			session.setAttribute("login", login);
			return "redirect:/home";
		}
		redirect.addFlashAttribute("errorMessage","Invalid Credentials");
		return "redirect:/login";
	}
	
	@GetMapping("/logout")
	public String logout(Model model,HttpSession session) {
		model.addAttribute("login",null);
		session.invalidate();
		return "redirect:/home";
	}
	
	@GetMapping("/forget")
	public String forgetPage(Model model,@ModelAttribute("forget") LoginModel forget) {
		model.addAttribute("forget",new LoginModel());
		return "Forget";
	}
	
	@PostMapping("/forget")
	public String otpPage(Model model,@ModelAttribute("forget") LoginModel forget,HttpSession session,RedirectAttributes redirect) {
		if(registering.getByEmail(forget.getEmail())==null){
			redirect.addFlashAttribute("errorMessage","Not Registered with this email Id");
			return "redirect:/forget";
		}
		
		int generate=random.nextInt(999999);
		email.sendEmailTo(forget.getEmail(), "OTP :"+ generate);
		OtpModel otp=new OtpModel();
		otp.setGeneratedOTP(String.valueOf(generate));
		session.setAttribute("forget", forget);
		model.addAttribute("otp",otp);
		return "otpPage";
	}
	
	@PostMapping("/otp")
	public String otpPage(@ModelAttribute("otp") OtpModel otp,Model model,RedirectAttributes redirect) {
		if(otp.getGeneratedOTP().compareTo(otp.getFormOTP())==0) {
			model.addAttribute("passwordForm", new LoginModel());
			redirect.addFlashAttribute("message","OTP Verification is Successfull");
			return "redirect:/setPassword";
		}
		redirect.addFlashAttribute("errorMessage","OTP Verification is Failed");
		return "redirect:/forget";
	}
	
	@GetMapping("/setPassword")
	public String setPassword(@ModelAttribute("otp") OtpModel otp,Model model) {
		return "SetPassword";
	}
	
	@PostMapping("/setPassword")
	public String setPassword(@ModelAttribute("password") LoginModel passwordForm,HttpSession session,RedirectAttributes redirect) {
		LoginModel forget=(LoginModel) session.getAttribute("forget");
		RegisterModel register=registering.getByEmail(forget.getEmail());
		register.setPassword(passwordForm.getPassword());
		redirect.addFlashAttribute("message", "Password Changed Successfully");
		registering.save(register);
		return "redirect:/login";
	}
	
}
