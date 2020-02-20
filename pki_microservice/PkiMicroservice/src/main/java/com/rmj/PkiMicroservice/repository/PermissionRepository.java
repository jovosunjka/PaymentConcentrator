package com.rmj.PkiMicroservice.repository;

import com.rmj.PkiMicroservice.model.Permission;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PermissionRepository extends Repository<Permission, Long> {

	Permission save(Permission permission);
	List<Permission> findAll();
	Permission findById(Long id);
	Permission findByName(String name);
	


}
