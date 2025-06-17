package com.example.olimpiadas25.controller;

import com.example.olimpiadas25.dto.request.RegistroVentaRequestDTO;
import com.example.olimpiadas25.persistence.entity.Estado;
import com.example.olimpiadas25.persistence.entity.PedidoEntity;
import com.example.olimpiadas25.persistence.repository.PedidoRepository;
import com.example.olimpiadas25.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;
    private final PedidoRepository pedidoRepository;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarVenta(@RequestBody RegistroVentaRequestDTO request) {
        PedidoEntity pedido = pedidoRepository.findById(request.getPedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        if (pedido.getEstado() != Estado.entregado) {
            return ResponseEntity.badRequest().body("El pedido no ha sido entregado aún");
        }

        pagoService.registrarVenta(pedido, request.getMedioPago());
        return ResponseEntity.ok("Venta registrada correctamente");
    }
}