package com.rmj.PkiMicroservice.service;

import java.util.Set;


public interface RoleService {
    Set<String> getPermission(String roleName);
}
