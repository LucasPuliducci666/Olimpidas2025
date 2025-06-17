package com.example.olimpiadas25.dto.request;

import com.example.olimpiadas25.persistence.entity.Estado;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PedidoRequestDTO {

        @NotNull
        private Long clienteId;

        @NotNull
        private Long paqueteId;

        @NotNull
        @Future
        private LocalDateTime fechainic;

        @NotNull
        @Future
        private LocalDateTime fechafin;

        @NotNull
        private Estado estado;






}



