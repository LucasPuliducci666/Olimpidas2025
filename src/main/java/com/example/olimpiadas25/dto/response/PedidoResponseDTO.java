package com.example.olimpiadas25.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PedidoResponseDTO {

        private Integer id;
        private ClientResponseDTO cliente;
        private PaquetResponseDTO paquete;
        private LocalDateTime fecha;
        private String estado;

}
