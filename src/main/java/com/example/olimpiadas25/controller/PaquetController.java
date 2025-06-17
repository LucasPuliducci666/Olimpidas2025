package com.example.olimpiadas25.controller;

import com.example.olimpiadas25.dto.request.PaquetRequestDTO;
import com.example.olimpiadas25.dto.response.PaquetResponseDTO;
import com.example.olimpiadas25.persistence.entity.PaquetEntity;
import com.example.olimpiadas25.service.ClientService;
import com.example.olimpiadas25.service.PaquetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paquetes")
public class PaquetController {

    private final PaquetService paquetService;

    public PaquetController(PaquetService paquetService) {
        this.paquetService = paquetService;
    }

    @GetMapping
    public List<PaquetResponseDTO> getAllPaquetes() {
        return paquetService.getAllPaquetes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaquetResponseDTO> getPaquetById(@PathVariable Long id) {
        return paquetService.getPaquetById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PaquetResponseDTO> createPaquet(@Valid @RequestBody PaquetRequestDTO dto) {
        return ResponseEntity.ok(paquetService.createPaquet(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaquetResponseDTO> updatePaquet(@PathVariable Long id, @RequestBody PaquetRequestDTO dto) {
        return paquetService.updatePaquet(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaquet(@PathVariable Long id) {
        return paquetService.deletePaquet(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}