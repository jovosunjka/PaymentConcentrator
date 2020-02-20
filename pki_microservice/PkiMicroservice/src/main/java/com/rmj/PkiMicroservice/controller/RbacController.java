package com.rmj.PkiMicroservice.controller;

import com.rmj.PkiMicroservice.dto.AddPermissonToRoleDTO;
import com.rmj.PkiMicroservice.dto.UserDTO;
import com.rmj.PkiMicroservice.model.Permission;
import com.rmj.PkiMicroservice.model.Role;
import com.rmj.PkiMicroservice.service.RbacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/rbac")
public class RbacController {

	@Autowired
	RbacService rbacService;

	@PreAuthorize("hasAuthority('ADD_ROLE_TO_USER')")
	@RequestMapping(value = "/add-role-to-user", method = RequestMethod.POST)
	public ResponseEntity addRoleToUser(@RequestParam("username") String username,
                                        @RequestParam("roleName") String roleName) {
		try {
			boolean retValue = rbacService.addRoleToUser(username, roleName);

			if (retValue)
				return new ResponseEntity<Boolean>(retValue, HttpStatus.CREATED);

			return new ResponseEntity<Boolean>(retValue, HttpStatus.OK);

		}

		catch (Exception e) {

			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasAuthority('ADD_PERMISSION_TO_ROLE')")
	@RequestMapping(value = "/add-permission-to-role", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addPermissionToRole(@RequestBody AddPermissonToRoleDTO dto) {
		try {
			boolean retValue = rbacService.addPermissionToRole(dto.getRoleId(), dto.getPermissionId());

			if (retValue)
				return new ResponseEntity(HttpStatus.CREATED);

			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		}

		catch (Exception e) {

			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasAuthority('ADD_REMOVE_PERMISSION_TO_ROLE')")
	@RequestMapping(value = "/remove-permission-to-role/{roleId}/{permissionId}", method = RequestMethod.DELETE)
	public ResponseEntity addPermissionToRole(@PathVariable("roleId") Long roleId, @PathVariable("permissionId") Long permissionId) {
		try {
			boolean retValue = rbacService.removePermissionFromRole(roleId, permissionId);

			if (retValue)
				return new ResponseEntity(HttpStatus.OK);

			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		}

		catch (Exception e) {

			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasAuthority('ADD_ROLE_AND_PERMISSION')")
	@RequestMapping(value = "/add-role", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addRole(@RequestParam("roleName") String roleName) {
		try {
			boolean retValue = rbacService.addRole(roleName);

			if (retValue)
				return new ResponseEntity<Boolean>(retValue, HttpStatus.CREATED);

			return new ResponseEntity<Boolean>(retValue, HttpStatus.OK);

		}

		catch (Exception e) {

			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasAuthority('ADD_ROLE_AND_PERMISSION')")
	@RequestMapping(value = "/add-permission", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addPermission(@RequestParam("permissionName") String permissionName) {
		try {
			boolean retValue = rbacService.addRole(permissionName);

			if (retValue)
				return new ResponseEntity<Boolean>(retValue, HttpStatus.CREATED);

			return new ResponseEntity<Boolean>(retValue, HttpStatus.OK);

		}

		catch (Exception e) {

			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasAuthority('CREATE_ADMINISTRATOR')")
	@RequestMapping(value = "/add_admin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> addAdmin(@RequestBody UserDTO userDTO) {
		try {
			boolean retValue = rbacService.addUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getName());
			if (retValue)
				return new ResponseEntity<Boolean>(retValue, HttpStatus.CREATED);
			return new ResponseEntity<Boolean>(retValue, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}

	}


	@PreAuthorize("hasAuthority('READ_ROLES_AND_PERMISSIONS')")
	@RequestMapping(value = "/get_roles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Role>> getRoles() {
		try {
			List<Role> roles = rbacService.getRolesWithPermissions();
			return new ResponseEntity<List<Role>>(roles, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<List<Role>>(HttpStatus.BAD_REQUEST);
		}

	}

	@PreAuthorize("hasAuthority('READ_ROLES_AND_PERMISSIONS')")
	@RequestMapping(value = "/get_permissions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Permission>> getPermissions() {
		try {
			List<Permission> permissions = rbacService.getPermissions();
			return new ResponseEntity<List<Permission>>(permissions, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<List<Permission>>(HttpStatus.BAD_REQUEST);
		}

	}


}
