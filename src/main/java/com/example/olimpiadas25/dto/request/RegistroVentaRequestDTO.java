package com.example.olimpiadas25.dto.request;

import com.example.olimpiadas25.persistence.entity.VentaEntity;
import lombok.Data;

@Data
public class RegistroVentaRequestDTO {
    private Long pedidoId;
    private VentaEntity.MedioPago medioPago;
}