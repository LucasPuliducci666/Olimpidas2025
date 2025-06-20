package com.example.olimpiadas25.controller;

import com.example.olimpiadas25.dto.response.AuthResponseDTO;
import com.example.olimpiadas25.persistence.entity.ClientEntity;
import com.example.olimpiadas25.persistence.entity.LoginRequest;
import com.example.olimpiadas25.persistence.repository.ClientRepository;
import com.example.olimpiadas25.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final ClientRepository clientRepository;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String jwtToken = jwtService.generateToken((UserDetails) authentication.getPrincipal());


        ClientEntity client = clientRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String rol = client.isAdmin() ? "admin" : "cliente";

        AuthResponseDTO response = new AuthResponseDTO(jwtToken, rol, client.getId_cliente());

        return ResponseEntity.ok(response);
    }
}