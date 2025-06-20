package com.example.olimpiadas25.dto.response;

import com.example.olimpiadas25.persistence.entity.MetodoPago;
import lombok.Data;

@Data
public class VentaResponseDTO {
    private Long id_venta;
    private Long pedidoId;
    private Long clienteId;
    private MetodoPago metodoPago;

}