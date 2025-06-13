package com.example.olimpiadas25.service;

import com.example.olimpiadas25.persistence.entity.ClientEntity;
import com.example.olimpiadas25.persistence.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientEntity> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<ClientEntity> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public ClientEntity createClient(ClientEntity client) {
        return clientRepository.save(client);
    }

    public Optional<ClientEntity> updateClient(Long id, ClientEntity updatedClient) {
        return clientRepository.findById(id).map(existing -> {
            existing.setNombre(updatedClient.getNombre());
            existing.setApellido(updatedClient.getApellido());
            existing.setEmail(updatedClient.getEmail());
            existing.setPassword(updatedClient.getPassword());
            existing.setTelefono(updatedClient.getTelefono());
            existing.setDireccion(updatedClient.getDireccion());
            return clientRepository.save(existing);
        });
    }

    public boolean deleteClient(Long id) {
        return clientRepository.findById(id).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
    }
}