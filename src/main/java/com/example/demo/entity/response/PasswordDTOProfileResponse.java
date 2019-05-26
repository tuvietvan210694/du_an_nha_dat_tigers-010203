package com.example.demo.entity.response;
import java.util.Map;
import com.example.demo.entity.dto.PasswordDTOProfile;

public class PasswordDTOProfileResponse {

	private PasswordDTOProfile passwordDTOProfile;
	private boolean validated;
	private Map<String, String> errorMessages;
	public PasswordDTOProfile getPasswordDTOProfile() {
		return passwordDTOProfile;
	}
	public void setPasswordDTOProfile(PasswordDTOProfile passwordDTOProfile) {
		this.passwordDTOProfile = passwordDTOProfile;
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
