package com.example.olimpiadas25.controller;


import com.example.olimpiadas25.dto.request.PedidoRequestDTO;
import com.example.olimpiadas25.dto.response.PedidoResponseDTO;
import com.example.olimpiadas25.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoResponseDTO> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> getPedidoById(@PathVariable Long id) {
        return pedidoService.getPedidoById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Optional<PedidoResponseDTO>> createPedido(@Valid @RequestBody PedidoRequestDTO dto) {
        return ResponseEntity.ok(pedidoService.createPedido(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> updatePedido(@PathVariable Long id, @RequestBody PedidoRequestDTO dto) {
        return pedidoService.updatePedido(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        return pedidoService.deletePedido(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}