package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.response.AdminResponse;

public interface AdminService {

	AdminResponse convertToResponse(Admin admin);

}
