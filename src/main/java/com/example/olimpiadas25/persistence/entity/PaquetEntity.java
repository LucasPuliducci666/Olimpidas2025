package com.example.olimpiadas25.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "paquetes")
public class PaquetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_paquete;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo")
    private Tipo tipo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Boolean Internacional;

    @Column(nullable = false, name = "capacidad")
    private Integer capacidad;

    @Column(nullable = false, name = "ubicacion")
    private String ubicacion;

    @ManyToMany(mappedBy = "paquetes")
    private List<PedidoEntity> pedidos = new ArrayList<>();

}