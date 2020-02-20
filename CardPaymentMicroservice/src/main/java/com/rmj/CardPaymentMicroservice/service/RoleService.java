package com.rmj.CardPaymentMicroservice.service;

import com.rmj.CardPaymentMicroservice.model.Role;

import java.util.Set;

public interface RoleService {
	
    Set<String> getPermissions(String roleName);

    Role getRole(String paying);
}
