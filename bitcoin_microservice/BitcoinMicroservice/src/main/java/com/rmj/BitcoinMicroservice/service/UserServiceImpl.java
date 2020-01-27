package com.rmj.BitcoinMicroservice.service;


import com.rmj.BitcoinMicroservice.models.*;
import com.rmj.BitcoinMicroservice.repository.UserRepository;
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

	@Autowired
	private FormFieldService formFieldService;


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
	public boolean register(String username, String password, String repeatedPassword, List<Property> properties, Currency currency) {
		if (username == null || password == null || repeatedPassword == null || properties == null)
			return false;

		if (username.equals("") || password.equals("") || repeatedPassword.equals(""))
			return false;

		if (properties.isEmpty())
			return false;

		if (exists(username))
			return false;

		List<Property> vProperties = checkAndReturnValidProperties(properties);
		if (vProperties == null) {
			return  false;
		}

		User user = new User(username, passwordEncoder.encode(password), vProperties, currency);
		Role role = roleService.getRole("PAYING");
		user.addRole(role);
		try {
			userRepository.save(user);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	private List<Property> checkAndReturnValidProperties(List<Property> properties) {
		List<FormField> formFields = formFieldService.getFormFields();
		List<String> formFieldNames = formFields.stream()
				.map(field -> field.getName())
				.collect(Collectors.toList());
		List<Property> vProperties = properties.stream()
				.filter(prop -> formFieldNames.contains(prop.getIdentifier()) && prop.getValue() != null
						&& !prop.getValue().equals(""))
				.collect(Collectors.toList());


		List<String> notOptionalFormFieldNames = formFields.stream()
				.filter(field -> !field.getOptional())
				.map(field -> field.getName())
				.collect(Collectors.toList());

		long notOptionalPropetiesCount = vProperties.stream()
													.filter(prop -> notOptionalFormFieldNames.contains(prop.getIdentifier()))
													.count();
		if (notOptionalFormFieldNames.size() != notOptionalPropetiesCount) {
			return null;
		}


		return vProperties;
	}

}