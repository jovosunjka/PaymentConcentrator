package com.rmj.PkiMicroservice.service;


import com.rmj.PkiMicroservice.model.Role;
import com.rmj.PkiMicroservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Set<String> getPermission(String roleName) {
        Role roleEntity = roleRepository.findByName(roleName);
        if(roleEntity == null) return new HashSet<>();

        return roleEntity.getPermissions().stream()
                .map(per -> per.getName())
                .collect(Collectors.toSet());
    }
}
