package com.example.olimpiadas25.dto.request;

import com.example.olimpiadas25.persistence.entity.MetodoPago;
import lombok.Data;

@Data
public class VentaRequestDTO {
    private Long pedidoId;
    private MetodoPago metodoPago;
}