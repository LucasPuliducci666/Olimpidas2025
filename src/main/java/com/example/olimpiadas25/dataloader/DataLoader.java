package com.example.olimpiadas25.dataloader;

import com.example.olimpiadas25.persistence.entity.*;
import com.example.olimpiadas25.persistence.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


import static com.example.olimpiadas25.persistence.entity.Tipo.*;
import static com.example.olimpiadas25.persistence.entity.Estado.*;


@Component
@RequiredArgsConstructor
public class DataLoader {

    @PersistenceContext
    private EntityManager entityManager;

    private final ClientRepository clientRepository;
    private final PaquetRepository paquetRepository;
    private final PedidoRepository pedidoRepository;

    @PostConstruct
    @Transactional
    public void loadData() {
        try {

            pedidoRepository.deleteAll();
            paquetRepository.deleteAll();
            clientRepository.deleteAll();

            ClientEntity client1 = new ClientEntity();
            client1.setNombre("Thiago");
            client1.setApellido("Gómez");
            client1.setEmail("TitiG@correo.com");
            client1.setPassword("666");
            client1.setTelefono("555111222");
            client1.setDireccion("MichoStreet");
            client1.setAdmin(false);
            client1 = clientRepository.save(client1);

            ClientEntity client2 = new ClientEntity();
            client2.setNombre("Ariel");
            client2.setApellido("Michalizin");
            client2.setEmail("ArielM@correo.com");
            client2.setPassword("777");
            client2.setTelefono("666333222");
            client2.setDireccion("BallStreet");
            client2.setAdmin(false);
            client2 = clientRepository.save(client2);

            PaquetEntity paquet1 = new PaquetEntity();
            paquet1.setTipo(pasaje);
            paquet1.setNombre("Paquete Familiar a Bariloche");
            paquet1.setDescripcion("Un viaje inolvidable a Rio Negro - Bariloche, diseñado para tres personas");
            paquet1.setPrecio(800000.0);
            paquet1.setInternacional(false);
            paquet1.setCapacidad(3);
            paquet1.setHotel("Hotel Plaza Bariloche");
            paquet1 = paquetRepository.save(paquet1);

            PaquetEntity paquet2 = new PaquetEntity();
            paquet2.setTipo(pasaje);
            paquet2.setNombre("Barco Familiar a Uruguay");
            paquet2.setDescripcion("Experiencia maritima individual para conocer Colonia");
            paquet2.setPrecio(700000.0);
            paquet2.setInternacional(true);
            paquet2.setCapacidad(1);
            paquet2.setHotel("Hotel de Ejemplo 1");
            paquet2 = paquetRepository.save(paquet2);

            PedidoEntity pedido1 = new PedidoEntity();
            pedido1.setCliente(client1);
            pedido1.setFecha(LocalDateTime.of(2025, 6, 12, 16, 30));
            pedido1.setEstado(pendiente);
            pedido1.setCliente(client1);
            pedido1.setPaquete(paquet1);
            pedido1 = pedidoRepository.save(pedido1);

            PedidoEntity pedido2 = new PedidoEntity();
            pedido2.setCliente(client2);
            pedido2.setFecha(LocalDateTime.of(2025, 8, 18, 18, 30));
            pedido2.setEstado(pendiente);
            pedido2.setCliente(client2);
            pedido2.setPaquete(paquet2);
            pedido2 = pedidoRepository.save(pedido2);

        } catch (Exception e) {
            System.err.println("❌ Error al cargar datos de prueba: " + e.getMessage());
            e.printStackTrace();
        } finally {

        }
    }

}
