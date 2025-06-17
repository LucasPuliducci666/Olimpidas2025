package com.example.olimpiadas25.service;

import com.example.olimpiadas25.persistence.entity.PedidoEntity;
import com.example.olimpiadas25.persistence.entity.VentaEntity;
import com.example.olimpiadas25.persistence.repository.VentaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final VentaRepository ventaRepository;

    @Transactional
    public void registrarVenta(PedidoEntity pedido, VentaEntity.MedioPago medioPago) {
        VentaEntity venta = new VentaEntity();
        venta.setCliente(pedido.getCliente());
        venta.setPedido(pedido);
        venta.setMontoTotal(pedido.getPaquete().getPrecio());
        venta.setFecha(LocalDate.now());
        venta.setMedioPago(medioPago);
        ventaRepository.save(venta);
    }
}
