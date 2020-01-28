package com.rmj.BitcoinMicroservice.service;

import com.rmj.BitcoinMicroservice.models.Role;

import java.util.Set;

public interface RoleService {
	
    Set<String> getPermissions(String roleName);

    Role getRole(String paying);
}
