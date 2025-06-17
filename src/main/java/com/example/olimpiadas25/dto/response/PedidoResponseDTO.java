package com.example.olimpiadas25.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PedidoResponseDTO {

        private Integer id;
        private ClientResponseDTO cliente;
        private PaquetResponseDTO paquete;
        private LocalDateTime fechainic;
        private LocalDateTime fechafin;
        private String estado;

}
