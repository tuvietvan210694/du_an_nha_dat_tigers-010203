package com.example.demo.service;

import com.example.demo.entity.Owner;
import com.example.demo.entity.response.OwnerResponse;

public interface OwnerService {

	OwnerResponse convertToOwnerResponse(Owner owner);

}
