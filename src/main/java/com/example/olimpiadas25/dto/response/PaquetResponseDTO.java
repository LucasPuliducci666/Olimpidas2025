package com.example.olimpiadas25.dto.response;

import com.example.olimpiadas25.persistence.entity.Tipo;
import lombok.Data;

@Data
public class PaquetResponseDTO {
    private Long Id;
    private Tipo tipo;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Boolean Internacional;
    private Integer capacidad;
    private String ubicacion;


}
