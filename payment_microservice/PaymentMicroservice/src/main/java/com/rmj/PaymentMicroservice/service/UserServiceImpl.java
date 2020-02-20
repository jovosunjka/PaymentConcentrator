package com.rmj.PaymentMicroservice.service;


import com.rmj.PaymentMicroservice.dto.PaymentAccountDTO;
import com.rmj.PaymentMicroservice.dto.RegistrationPaymentAccountDTO;
import com.rmj.PaymentMicroservice.exception.NotFoundException;
import com.rmj.PaymentMicroservice.exception.UserNotFoundException;
import com.rmj.PaymentMicroservice.model.PaymentAccount;
import com.rmj.PaymentMicroservice.model.Role;
import com.rmj.PaymentMicroservice.model.User;
import com.rmj.PaymentMicroservice.repository.UserRepository;
import com.rmj.PaymentMicroservice.service.UserService;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
	private PaymentAccountService paymentAccountService;


	private static PasswordGenerator passwordGenerator = new PasswordGenerator();


	/*@EventListener(ApplicationReadyEvent.class)
	public void writeEntitiesInDBWithEncryptedSomeAttributes() {
		userRepository.save(new User("Admin","admin", "$2y$12$TxHOkrOHJBW39ZuH0hxE0e8Jfk53XEQI66R9KXrmaIgZn.SdRKYei", new ArrayList<PaymentAccount>()));
		userRepository.save(new User("Magazine1","magazine1", "$2y$12$aO9J/cE8XdZAQ3VWPu5nneHeRPIklTo53pHCnheYsVz7gjA1fAUIa", new ArrayList<PaymentAccount>()));
		userRepository.save(new User("Magazine2","magazine2", "$2y$12$FMTUbM2zAOXIdRaJKGC8we/pr7qAVz2XEG1l/RKMvIrfTJ8aYCf3S", new ArrayList<PaymentAccount>()));
		userRepository.save(new User("Magazine3","magazine3", "$2y$12$V7WT28jfRij5c7tMaKqir.2gIytNBhP0cgehYDzHs1A5ct1tT3Yvy", new ArrayList<PaymentAccount>()));
		userRepository.save(new User("Magazine4","magazine4", "$2y$12$sVnSKgibZD2JbgaSIPdCWO2FUPiV8wasotmvFCAbSJakW327ZP77S", new ArrayList<PaymentAccount>()));
		userRepository.save(new User("Magazine5","magazine5", "$2y$12$vKbakU5ZxjA3oikG1p4m9ufRh/Tkmgppn7RnkJ7S4zBpVEeNypLBO", new ArrayList<PaymentAccount>()));
		userRepository.save(new User("Magazine6","magazine6", "$2y$12$jHTppI44NNwzLFv3JOgcmuqo8wxCkHq9aW/YKK3.T96McX1LPp5SC", new ArrayList<PaymentAccount>()));
		userRepository.save(new User("Magazine7","magazine7", "$2y$12$RS5k3aTzBmmAFU1Ct20T0eShFNah7V2orakxbLZarbTQVx/1/vUim", new ArrayList<PaymentAccount>()));
		userRepository.save(new User("Magazine8","magazine8", "$2y$12$zKna0dLdcfFKVlxxIXYHQuefuqZpEWuOUxLk9eJn5Dzva3gfANNQO", new ArrayList<PaymentAccount>()));
		userRepository.save(new User("Magazine9","magazine9", "$2y$12$ofIF6vC/IVAJqnR9lalGUe5EfpWlhk2Wq6VmPGXmb44T3GEuzrHRa", new ArrayList<PaymentAccount>()));
	}*/

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@Override
	public User getLoggedUser() throws UserNotFoundException {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		try {
			org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth
					.getPrincipal();
			return userRepository.findByUsername(user.getUsername()).orElse(null);
		} catch (Exception e) {
			throw new UserNotFoundException("Logged user not found!");
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
	public boolean register(String name, String username, String password, String repeatedPassword, List<PaymentAccountDTO> accountDTOs) {
		if (name == null || username == null || password == null || repeatedPassword == null || accountDTOs == null)
			return false;

		if (name.equals("") || username.equals("") || password.equals("") || repeatedPassword.equals(""))
			return false;

		if (accountDTOs.isEmpty())
			return false;

		if (exists(username))
			return false;

		List<PaymentAccount> accounts = new ArrayList<PaymentAccount>();
		for(PaymentAccountDTO a : accountDTOs) {
			String accountUsername = a.getType().concat("_").concat(name);
			String accountPassword = generatePassword();

			RegistrationPaymentAccountDTO registrationPaymentAccountDTO = new RegistrationPaymentAccountDTO(accountUsername,
																		accountPassword, accountPassword, a.getProperties(), a.getCurrency());
			try {
				paymentAccountService.registerPaymentAccount(a.getType(), registrationPaymentAccountDTO);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			PaymentAccount account = new PaymentAccount(a.getType(), accountUsername, accountPassword,
														a.getCurrency());
			accounts.add(account);
		}


		User user = new User(name, username, passwordEncoder.encode(password), accounts);
		Role role = roleService.getRole("PAYING");
		user.addRole(role);
		try {
			userRepository.save(user);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	private String generatePassword() {
		CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
		CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
		lowerCaseRule.setNumberOfCharacters(2);

		CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
		CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
		upperCaseRule.setNumberOfCharacters(2);

		CharacterData digitChars = EnglishCharacterData.Digit;
		CharacterRule digitRule = new CharacterRule(digitChars);
		digitRule.setNumberOfCharacters(2);

		CharacterData specialChars = new CharacterData() {
			public String getErrorCode() {
				return "special_chars_error_code";
			}

			public String getCharacters() {
				return "!@#$%^&*()_+";
			}
		};
		CharacterRule splCharRule = new CharacterRule(specialChars);
		splCharRule.setNumberOfCharacters(2);

		String password = passwordGenerator.generatePassword(10, splCharRule, lowerCaseRule,
				upperCaseRule, digitRule);
		return password;
	}

}