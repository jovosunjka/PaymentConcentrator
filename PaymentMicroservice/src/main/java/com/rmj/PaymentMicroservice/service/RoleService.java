package com.rmj.PaymentMicroservice.service;

import java.util.Set;

public interface RoleService {
	
    Set<String> getPermissions(String roleName);
}
