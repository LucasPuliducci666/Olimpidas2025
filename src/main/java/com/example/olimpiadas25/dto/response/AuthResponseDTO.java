package com.example.olimpiadas25.dto.response;

public class AuthResponseDTO {
    private String token;
    private String rol;
    private Long id_cliente;

    public AuthResponseDTO(String token, String rol, Long id) {
        this.token = token;
        this.rol = rol;
        this.id_cliente = id;
    }

    public String getToken() {
        return token;
    }

    public String getRol() {
        return rol;
    }

    public Long getId() {
        return id_cliente;
    }
}
