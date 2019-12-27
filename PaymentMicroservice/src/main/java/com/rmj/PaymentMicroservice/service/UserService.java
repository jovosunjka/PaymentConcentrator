package com.rmj.PaymentMicroservice.service;


import com.rmj.PaymentMicroservice.model.User;

public interface UserService {

	User getLoggedUser() throws Exception;
	
    void save(User user) throws Exception;

    User getUser(String username);
    
    User getUser(String username, String password);

    boolean exists(String username);

	boolean register(String name, String username, String password, String repeatedPassword);

}
