package com.example.olimpiadas25.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "ventas")
@Data
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double montoTotal;

    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private MedioPago medioPago;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClientEntity cliente;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    public enum MedioPago {
        TARJETA_CREDITO,
        TARJETA_DEBITO,
        TRANSFERENCIA,
        MERCADO_PAGO
    }
}