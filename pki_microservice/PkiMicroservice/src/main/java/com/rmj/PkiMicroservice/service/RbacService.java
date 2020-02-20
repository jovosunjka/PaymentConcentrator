package com.rmj.PkiMicroservice.service;


import com.rmj.PkiMicroservice.model.Permission;
import com.rmj.PkiMicroservice.model.Role;

import java.util.List;

public interface RbacService {

	boolean addRole(String role);
	boolean addPermission(String permissionName);
	boolean addPermissionToRole(Long roleId, Long permissionId);
	boolean addRoleToUser(String userName, String roleName);
	boolean addUser(String username, String password, String name);


	List<Role> getRolesWithPermissions();

	boolean removePermissionFromRole(Long roleId, Long permissionId);

	List<Permission> getPermissions();
}
