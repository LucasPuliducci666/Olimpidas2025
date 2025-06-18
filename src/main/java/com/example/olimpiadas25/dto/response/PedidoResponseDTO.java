package com.example.olimpiadas25.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoResponseDTO {

        private Integer id;
        private ClientResponseDTO cliente;
        private List<PaquetResponseDTO> paquetes;
        private LocalDateTime fechainic;
        private LocalDateTime fechafin;
        private String estado;
}
