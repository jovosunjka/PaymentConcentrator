package com.rmj.IntegrationMicroservice.service;

import com.rmj.IntegrationMicroservice.exception.AlreadyExistsException;
import com.rmj.IntegrationMicroservice.model.Client;
import com.rmj.IntegrationMicroservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public void add(Client client) {
        if (clientRepository.exists(Example.of(client))) {
            throw new AlreadyExistsException(
                    String.format("There is already a client with the name %s", client.getName()));
        }
        clientRepository.save(client);
    }
}
