package com.example.olimpiadas25.service;

import com.example.olimpiadas25.dto.request.VentaRequestDTO;
import com.example.olimpiadas25.dto.response.VentaResponseDTO;
import com.example.olimpiadas25.persistence.entity.PedidoEntity;
import com.example.olimpiadas25.persistence.entity.VentaEntity;
import com.example.olimpiadas25.persistence.repository.PedidoRepository;
import com.example.olimpiadas25.persistence.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final PedidoRepository pedidoRepository;

    public VentaResponseDTO registrarVenta(VentaRequestDTO dto) {
        PedidoEntity pedido = pedidoRepository.findById(dto.getPedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        VentaEntity venta = new VentaEntity();
        venta.setPedido(pedido);
        venta.setCliente(pedido.getCliente());
        venta.setMetodoPago(dto.getMetodoPago());

        VentaEntity saved = ventaRepository.save(venta);

        VentaResponseDTO response = new VentaResponseDTO();
        response.setId_venta(saved.getId_venta());
        response.setPedidoId(saved.getPedido().getId().longValue());
        response.setClienteId(saved.getCliente().getId_cliente().longValue());
        response.setMetodoPago(saved.getMetodoPago());

        return response;
    }
}