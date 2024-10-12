package com.example.FixMyHome.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OtpModel {
	private String GeneratedOTP;
	private String FormOTP;
	
	public String getGeneratedOTP() {
		return GeneratedOTP;
	}
	public void setGeneratedOTP(String generatedOTP) {
		GeneratedOTP = generatedOTP;
	}
	public String getFormOTP() {
		return FormOTP;
	}
	public void setFormOTP(String formOTP) {
		FormOTP = formOTP;
	}
	
}
