package com.example.demo.entity.response;
import java.util.Map;
import com.example.demo.entity.dto.SignUp;

public class SignupDTOResponse {
	
	private SignUp signup;
	private boolean validated;
	private Map<String, String> errorMessages;
	public SignUp getSignup() {
		return signup;
	}
	public void setSignup(SignUp signup) {
		this.signup = signup;
	}
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}
	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	
}
