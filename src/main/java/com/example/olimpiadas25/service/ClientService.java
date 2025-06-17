package com.example.olimpiadas25.service;

import com.example.olimpiadas25.dto.response.ClientResponseDTO;
import com.example.olimpiadas25.dto.request.ClientRequestDTO;
import com.example.olimpiadas25.persistence.entity.ClientEntity;
import com.example.olimpiadas25.persistence.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private ClientResponseDTO toResponseDTO(ClientEntity client) {
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setId(client.getId_cliente());
        dto.setNombre(client.getNombre());
        dto.setApellido(client.getApellido());
        dto.setEmail(client.getEmail());
        dto.setTelefono(client.getTelefono());
        return dto;
    }

    private void fromRequestDTO(ClientRequestDTO dto, ClientEntity client) {
        client.setNombre(dto.getNombre());
        client.setApellido(dto.getApellido());
        client.setEmail(dto.getEmail());
        client.setPassword(dto.getPassword());
        client.setTelefono(dto.getTelefono());
        client.setDireccion(dto.getDireccion());
        client.setAdmin(dto.getAdmin());
    }

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientResponseDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Optional<ClientResponseDTO> getClientById(Long id) {
        return clientRepository.findById(id).map(this::toResponseDTO);
    }

    public ClientResponseDTO createClient(ClientRequestDTO dto) {
        ClientEntity client = new ClientEntity();
        fromRequestDTO(dto, client);
        return toResponseDTO(clientRepository.save(client));
    }

    public Optional<ClientResponseDTO> updateClient(Long id, ClientRequestDTO dto) {
        return clientRepository.findById(id).map(existing -> {
            fromRequestDTO(dto, existing);
            return toResponseDTO(clientRepository.save(existing));
        });
    }

    public boolean deleteClient(Long id) {
        return clientRepository.findById(id).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
    }
}