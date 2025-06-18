package com.example.olimpiadas25.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClientEntity cliente;

    @ManyToMany
    @JoinTable(
            name = "pedido_paquete",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "paquete_id")
    )
    private List<PaquetEntity> paquetes = new ArrayList<>();

    private LocalDateTime fechainic = LocalDateTime.now();

    private LocalDateTime fechafin = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "Estado")
    private Estado estado;


}