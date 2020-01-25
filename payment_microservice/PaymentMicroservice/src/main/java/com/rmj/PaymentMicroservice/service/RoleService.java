package com.rmj.PaymentMicroservice.service;

import com.rmj.PaymentMicroservice.model.Role;

import java.util.Set;

public interface RoleService {
	
    Set<String> getPermissions(String roleName);

    Role getRole(String paying);
}
