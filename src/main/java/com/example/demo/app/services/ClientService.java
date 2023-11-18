package com.example.demo.app.services;

import com.example.demo.app.entities.Client;

import java.util.List;

public interface ClientService {

    List<Client> getClients();
    void saveClient(Client client);
    Client getClient(int id);
    void deleteClient(int id);
}
