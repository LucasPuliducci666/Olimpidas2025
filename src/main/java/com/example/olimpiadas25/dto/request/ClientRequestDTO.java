package com.example.olimpiadas25.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no debe superar los 50 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "El apellido no debe superar los 50 caracteres")
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe proporcionar un email válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 12, message = "La contraseña debe tener entre 6 y 12 caracteres")
    private String password;

    @Size(max = 20, message = "El teléfono no debe superar los 20 caracteres")
    @NotBlank(message = "El telefono es obligatorio")
    private String telefono;

    @Size(max = 100, message = "La dirección no debe superar los 100 caracteres")
    @NotBlank(message = "La direccion es obligatoria")
    private String direccion;

    private boolean admin = false;
}