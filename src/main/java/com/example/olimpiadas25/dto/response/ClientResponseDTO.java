package com.example.olimpiadas25.dto.response;

import lombok.Data;

@Data
public class ClientResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
}