package com.example.demo.entity.response;

import java.util.Map;
import com.example.demo.entity.dto.AssignBrokerWithCustomerDTO;
public class AssignBrokerWithCustomerDTOResponse {
	
	private AssignBrokerWithCustomerDTO assignBrokerWithCustomerDTO;
	private boolean validated;
	private Map<String, String> errorMessages;
	public AssignBrokerWithCustomerDTO getAssignBrokerWithCustomerDTO() {
		return assignBrokerWithCustomerDTO;
	}
	public void setAssignBrokerWithCustomerDTO(AssignBrokerWithCustomerDTO assignBrokerWithCustomerDTO) {
		this.assignBrokerWithCustomerDTO = assignBrokerWithCustomerDTO;
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
