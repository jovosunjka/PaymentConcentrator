package com.rmj.PkiMicroservice.service;


import com.rmj.PkiMicroservice.model.User;

public interface UserService {

	User getLoggedUser() throws Exception;
	
    void save(User user) throws Exception;

    User getUser(String username);
    
    User getUser(String username, String password);

    boolean exists(String username);

	boolean register(String username, String password, String repeatedPassword);

}
