package com.rmj.PayPalMicroservice.service;

import com.rmj.PayPalMicroservice.model.Role;

import java.util.Set;

public interface RoleService {
	
    Set<String> getPermissions(String roleName);

    Role getRole(String paying);
}
