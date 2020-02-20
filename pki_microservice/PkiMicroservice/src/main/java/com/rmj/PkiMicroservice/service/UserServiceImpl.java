package com.rmj.PkiMicroservice.service;


import com.rmj.PkiMicroservice.model.*;
import com.rmj.PkiMicroservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;



	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public User getLoggedUser() throws Exception {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		try {
			org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth
					.getPrincipal();
			return userRepository.findByUsername(user.getUsername()).orElse(null);
		} catch (Exception e) {
			throw new Exception("User not found!");
		}

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void save(User user) throws Exception {
		userRepository.save(user);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public User getUser(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public User getUser(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password).orElse(null);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public boolean exists(String username) {
		return userRepository.existsByUsername(username);
	}


	@Override
	public boolean register(String username, String password, String repeatedPassword) {
		if (username == null || password == null || repeatedPassword == null)
			return false;

		if (username.equals("") || password.equals("") || repeatedPassword.equals(""))
			return false;

		if (exists(username))
			return false;


		User user = new User(username, passwordEncoder.encode(password));
		try {
			userRepository.save(user);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}