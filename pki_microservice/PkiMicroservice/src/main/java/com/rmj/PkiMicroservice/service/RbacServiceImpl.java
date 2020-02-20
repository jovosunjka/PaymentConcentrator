package com.rmj.PkiMicroservice.service;


import com.rmj.PkiMicroservice.model.Permission;
import com.rmj.PkiMicroservice.model.Role;
import com.rmj.PkiMicroservice.model.User;
import com.rmj.PkiMicroservice.repository.PermissionRepository;
import com.rmj.PkiMicroservice.repository.RoleRepository;
import com.rmj.PkiMicroservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RbacServiceImpl implements RbacService {
	
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PermissionRepository permissionRepository;
	@Autowired
	UserRepository userRepository;

	public boolean addRole(String roleName) {
		Role role = new Role(roleName);
		role = roleRepository.save(role);
		if (role!=null) return true;
		return false;
		
	}

	@Override
	public boolean addPermission(String permissionName) {
		Permission permission = new Permission(permissionName);
		permission = permissionRepository.save(permission);
		if (permission!=null) return true;
		return false;
	}

	@Override
		public boolean addUser(String username, String password, String name) {
			if (username == null || name == null || password == null)
				return false;
			if (username == "" || password == "")
				return false;
			if (existsUsername(username))
				return false;
			User user = new User(username,password);
			try {
				userRepository.save(user);
			} catch (Exception e) {
				return false;
			}
			return true;
	}

	@Override
	public List<Role> getRolesWithPermissions() {
		return roleRepository.findAll();
	}

	@Override
	public boolean removePermissionFromRole(Long roleId, Long permissionId) {
		try {
			Role role = roleRepository.findById(roleId).orElse(null);
			if(role == null) return false;

			Permission permission = null;
			for(Permission p : role.getPermissions()) {
				if(p.getId().longValue() == roleId.longValue()) {
					permission = p;
					break;
				}
			}

			role.getPermissions().remove(permission);
			roleRepository.save(role);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public List<Permission> getPermissions() {
		return permissionRepository.findAll();
	}


	public boolean existsUsername(String username) {

		User user = userRepository.findByUsername(username).orElse(null);

		return user != null;
}

	@Override
	public boolean addPermissionToRole(Long roleId, Long permissionId) {
		Role role = roleRepository.findById(roleId).orElse(null);
		if (role == null) return false;
		Permission permission = permissionRepository.findById(permissionId);
		if (permission == null) return false;
		role.getPermissions().add(permission);
		roleRepository.save(role);
		return true;
	}

	@Override
	public boolean addRoleToUser(String userName, String roleName) {
		Role role = roleRepository.findByName(roleName);
		if (role == null) return false;
		User user = userRepository.findByUsername(userName).orElse(null);
		if (user == null) return false;
		user.getRoles().add(role);
		userRepository.save(user);
		return true;
	}	

}
