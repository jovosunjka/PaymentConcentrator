package com.rmj.PaymentMicroservice.service;


import com.rmj.PaymentMicroservice.dto.PaymentAccountDTO;
import com.rmj.PaymentMicroservice.exception.UserNotFoundException;
import com.rmj.PaymentMicroservice.model.User;

import java.util.List;

public interface UserService {

	User getLoggedUser() throws UserNotFoundException;
	
    void save(User user) throws Exception;

    User getUser(String username);
    
    User getUser(String username, String password);

    boolean exists(String username);

	boolean register(String name, String username, String password, String repeatedPassword, List<PaymentAccountDTO> accountDTOs);

}
