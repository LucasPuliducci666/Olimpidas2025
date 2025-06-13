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

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataLoader {

    @PersistenceContext
    private EntityManager entityManager;

    private final ClientRepository clientRepository;
    private final PaquetRepository paquetRepository;
    private final PedidoRepository PedidoRepository;

    @PostConstruct
    @Transactional
    public void loadData() {
        try {
            ClientEntity client1 = new ClientEntity();
            client1.setNombre("Thiago");
            client1.setApellido("Gómez");
            client1.setEmail("TitiG@correo.com");
            client1.setPassword("666");
            client1.setTelefono("555111222");
            client1.setDireccion("MichoStreet");
            client1 = clientRepository.save(client1);

            ClientEntity client2 = new ClientEntity();
            client2.setNombre("Ariel");
            client2.setApellido("Michalizin");
            client2.setEmail("ArielM@correo.com");
            client2.setPassword("777");
            client2.setTelefono("666333222");
            client2.setDireccion("BallStreet");
            client2 = clientRepository.save(client2);

            PaquetEntity paquet1 = new PaquetEntity();
            paquet1.setTipo("Vuelo");
            paquet1.setNombre("Vuelo Aereo 1");
            paquet1.setDescripcion("Skibidi toilet ohio rizzler viaje");
            paquet1.setPrecio(200.0);
            paquet1.setIsdisp(true);
            paquet1 = paquetRepository.save(paquet1);

            PaquetEntity paquet2 = new PaquetEntity();
            paquet2.setTipo("Navegacion");
            paquet2.setNombre("Barco Aereo 2");
            paquet2.setDescripcion("El toto submarino es un viaje de ida");
            paquet2.setPrecio(600.0);
            paquet2.setIsdisp(false);
            paquet2 = paquetRepository.save(paquet2);

            PedidoEntity pedido1 = new PedidoEntity();
            pedido1.setClient(client1);
            pedido1.setFecha(LocalDate.of(2025, 6, 12));
            pedido1.setEstado("Demorado");
            pedido1.setCliente(client1);
            pedido1.setPaquete(paquet1);

        } catch (Exception e) {
            System.err.println("❌ Error al cargar datos de prueba: " + e.getMessage());
            e.printStackTrace();
        } finally {

        }
    }

}
