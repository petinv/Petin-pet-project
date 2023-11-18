package com.example.demo.app.repository;

import com.example.demo.app.entities.Client;

import java.util.List;

public interface ClientRepository {

    List<Client> getClients();
    void saveClient(Client client);
    Client getClient(int id);
    void deleteClient(int id);
}
