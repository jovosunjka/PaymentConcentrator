package com.rmj.BitcoinMicroservice.service;


import com.rmj.BitcoinMicroservice.models.Currency;
import com.rmj.BitcoinMicroservice.models.Property;
import com.rmj.BitcoinMicroservice.models.User;

import java.util.List;

public interface UserService {

	User getLoggedUser() throws Exception;
	
    void save(User user) throws Exception;

    User getUser(String username);
    
    User getUser(String username, String password);

    boolean exists(String username);

	boolean register(String username, String password, String repeatedPassword, List<Property> properties, Currency currency);

}
