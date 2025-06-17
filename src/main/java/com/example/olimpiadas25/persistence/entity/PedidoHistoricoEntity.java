package com.example.olimpiadas25.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "pedidos_historico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoHistoricoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechainic;
    private LocalDate fechafin;
    private Double precio;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClientEntity cliente;

    @ManyToOne
    @JoinColumn(name = "paquete_id")
    private PaquetEntity paquete;

    public enum Estado {
        entregado, cancelado
    }
}