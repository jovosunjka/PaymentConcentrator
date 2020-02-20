package com.rmj.CardPaymentMicroservice.service;


import com.rmj.CardPaymentMicroservice.model.Currency;
import com.rmj.CardPaymentMicroservice.model.Property;
import com.rmj.CardPaymentMicroservice.model.User;

import java.util.List;

public interface UserService {

	User getLoggedUser() throws Exception;
	
    void save(User user) throws Exception;

    User getUser(String username);
    
    User getUser(String username, String password);

    boolean exists(String username);

	boolean register(String username, String password, String repeatedPassword, List<Property> properties, Currency currency);

}
