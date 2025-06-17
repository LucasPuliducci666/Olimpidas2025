package com.example.olimpiadas25.dto.request;

import com.example.olimpiadas25.persistence.entity.Tipo;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PaquetRequestDTO {

    @NotNull
    private Tipo tipo;

    @Size(max = 50, message = "El nombre no debe superar los 50 caracteres")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Size(min = 10, message = "La descripcion debe superar los 10 caracteres")
    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    @NotNull
    @Positive
    private Double precio;

    @NotNull
    private Boolean Internacional;

    @NotNull
    @Positive
    private Integer capacidad;

    @Size(max = 50, message = "El nombre del hotel no debe superar los 50 caracteres")
    @NotBlank(message = "El nombre del hotel es obligatoria")
    private String hotel;

}


