package com.rmj.PkiMicroservice.controller;

import com.rmj.PkiMicroservice.dto.*;
import com.rmj.PkiMicroservice.security.TokenUtils;
import com.rmj.PkiMicroservice.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;
	
	@Autowired
    TokenUtils tokenUtils;

	@Autowired
	private PasswordEncoder passwordEncoder;


    @RequestMapping(value="/login", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody LoginUserDTO userDTO) {
        try {
        	// Perform the authentication
        	UsernamePasswordAuthenticationToken userInfo = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(userInfo);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Reload user details so we can generate token
            //authentication.getDetails()
            UserDetails details = userDetailsService.loadUserByUsername(userDTO.getUsername());
            String generatedToken = tokenUtils.generateToken(details);
            Map<String,String> result = new HashMap<>();
            result.put("token",generatedToken);
            return new ResponseEntity<TokenDTO>(new TokenDTO(generatedToken), HttpStatus.OK);
        }
    	catch (Exception e) {
            return new ResponseEntity<String>("Invalid login", HttpStatus.BAD_REQUEST);
        }
    	
    }

    @RequestMapping(value ="/registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registrate(@RequestBody RegistrationUserDTO userDTO) {

        boolean registered = userService.register(userDTO.getUsername(), userDTO.getPassword(),
        		userDTO.getRepeatedPassword());

        if(registered){
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    
    @RequestMapping(value ="/registration-all", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registrate(@RequestBody RegistrationUserDTOs userDTOs) {
    	if (userDTOs == null || userDTOs.getUsers() == null || userDTOs.getUsers().isEmpty()) {
    		return new ResponseEntity(HttpStatus.BAD_REQUEST);
    	}
    	
    	RegistrationResultDTOs registrationResultDTOs = new RegistrationResultDTOs();
    	
    	userDTOs.getUsers().stream()
    				.forEach(user -> {
    					boolean registered = userService.register(user.getUsername(), user.getPassword(),
                                user.getRepeatedPassword());
    					registrationResultDTOs.addRegistrationResult(user.getUsername(), registered);
    				});
    	
        return new ResponseEntity<RegistrationResultDTOs>(registrationResultDTOs, HttpStatus.OK);
    }


}
