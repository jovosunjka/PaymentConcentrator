package com.rmj.PaymentMicroservice.service;

import com.rmj.PaymentMicroservice.model.Role;
import com.rmj.PaymentMicroservice.repository.RoleRepository;
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
    public Set<String> getPermissions(String roleName) {
        Role role = roleRepository.findByName(roleName).orElse(null);
        if(role == null) return new HashSet<>();

        return role.getPermissions().stream()
                .map(per -> per.getName())
                .collect(Collectors.toSet());
    }

    @Override
    public Role getRole(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role (name=".concat(name).concat(") not found!")));
    }
}
