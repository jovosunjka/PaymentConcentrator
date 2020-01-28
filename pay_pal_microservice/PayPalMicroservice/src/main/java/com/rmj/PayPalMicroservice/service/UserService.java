package com.rmj.PayPalMicroservice.service;


import com.rmj.PayPalMicroservice.model.Currency;
import com.rmj.PayPalMicroservice.model.Property;
import com.rmj.PayPalMicroservice.model.User;

import java.util.List;

public interface UserService {

	User getLoggedUser() throws Exception;
	
    void save(User user) throws Exception;

    User getUser(String username);
    
    User getUser(String username, String password);

    boolean exists(String username);

	boolean register(String username, String password, String repeatedPassword, List<Property> properties, Currency currency);

}
