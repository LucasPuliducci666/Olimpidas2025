package com.example.olimpiadas25.persistence.entity;

public enum Estado {
    pendiente,
    entregado,
    cancelado;

    public static Estado fromString(String value) {
        return Estado.valueOf(value.toLowerCase());
    }
}
