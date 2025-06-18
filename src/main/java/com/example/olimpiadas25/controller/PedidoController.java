package com.example.olimpiadas25.controller;


import com.example.olimpiadas25.dto.request.PedidoRequestDTO;
import com.example.olimpiadas25.dto.response.PedidoResponseDTO;
import com.example.olimpiadas25.persistence.entity.PedidoEntity;
import com.example.olimpiadas25.service.EmailSenderService;
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
    private final EmailSenderService emailSenderService;

    public PedidoController(PedidoService pedidoService, EmailSenderService emailSenderService) {
        this.pedidoService = pedidoService;
        this.emailSenderService = emailSenderService;
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

    @PutMapping("/{id}/entregar")
    public ResponseEntity<PedidoResponseDTO> entregarPedido(@PathVariable Integer id) {
        PedidoEntity pedido = emailSenderService.entregarPedido(id);
        return ResponseEntity.ok(pedidoService.toResponseDTO(pedido));
    }

    @PutMapping("/{id}/anular")
    public ResponseEntity<PedidoEntity> anularPedido(@PathVariable Integer id) {
        PedidoEntity pedido = emailSenderService.anularPedido(id);
        return ResponseEntity.ok(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        return pedidoService.deletePedido(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}