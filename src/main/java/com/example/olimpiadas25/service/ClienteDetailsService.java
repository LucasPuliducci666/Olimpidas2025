package com.example.olimpiadas25.service;

import com.example.olimpiadas25.persistence.entity.ClientEntity;
import com.example.olimpiadas25.persistence.repository.ClientRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;

    public ClienteDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ClientEntity cliente = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado con email: " + email));

        return new User(cliente.getEmail(), cliente.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }
}