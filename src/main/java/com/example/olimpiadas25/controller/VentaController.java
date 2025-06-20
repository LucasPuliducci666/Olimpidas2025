package com.example.olimpiadas25.controller;

import com.example.olimpiadas25.dto.request.VentaRequestDTO;
import com.example.olimpiadas25.dto.response.VentaResponseDTO;
import com.example.olimpiadas25.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaResponseDTO> registrarVenta(@RequestBody VentaRequestDTO dto) {
        VentaResponseDTO response = ventaService.registrarVenta(dto);
        return ResponseEntity.ok(response);
    }
}
