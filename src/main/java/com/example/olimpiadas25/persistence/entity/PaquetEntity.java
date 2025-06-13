package com.example.olimpiadas25.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;

import java.util.List;

@Data
@Entity
@Table(name = "paquetes")
public class PaquetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_paquete;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private boolean Isdisp;

    @OneToMany(mappedBy = "paquete", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoEntity> pedidos;

}