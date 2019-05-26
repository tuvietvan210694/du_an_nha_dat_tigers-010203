package com.example.demo.service.Impl;
import com.example.demo.entity.Owner;
import com.example.demo.entity.response.OwnerResponse;
import com.example.demo.service.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {

	@Override
	public OwnerResponse convertToOwnerResponse(Owner owner) {
		OwnerResponse o = new OwnerResponse();
		o.setId(owner.getId());
		o.setIdCard(owner.getIdCard());
		o.setOwnerName(owner.getOwnerName());
		o.setState(owner.getTrangThai());
		o.setNameAdmin(owner.getAdmin().getUser().getNameUser());
		return o;
	}

}
