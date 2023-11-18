package com.example.demo.app.services;

import com.example.demo.app.entities.Client;
import com.example.demo.app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// @Service используется для аннотирования
// классов реализации сервисов.
// @Transactional применяется к сервисному слою
// для поддержки транзакций.
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository repository;

    @Override
    @Transactional
    public List<Client> getClients() {
        return repository.getClients();
    }

    @Override
    @Transactional
    public void saveClient(Client client) {
        repository.saveClient(client);
    }

    @Override
    @Transactional
    public Client getClient(int id) {
        return repository.getClient(id);
    }

    @Override
    @Transactional
    public void deleteClient(int id) {
        repository.deleteClient(id);
    }
}
