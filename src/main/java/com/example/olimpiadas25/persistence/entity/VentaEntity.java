package com.example.olimpiadas25.persistence.entity;

import com.example.olimpiadas25.persistence.entity.*;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ventas")
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long id_venta;


    @OneToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private PedidoEntity pedido;


    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClientEntity cliente;


    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodoPago;
}