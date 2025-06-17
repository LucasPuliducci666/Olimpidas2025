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
            client1.setNombre("Cristian");
            client1.setApellido("Azocar");
            client1.setEmail("cristhianazocar10@gmail.com");
            client1.setPassword("bocateamo123");
            client1.setTelefono("555111222");
            client1.setDireccion("Castelli 625");
            client1.setAdmin(false);
            client1 = clientRepository.save(client1);

            ClientEntity client2 = new ClientEntity();
            client2.setNombre("Ariel");
            client2.setApellido("Michalizin");
            client2.setEmail("ArielM@correo.com");
            client2.setPassword("fireball99");
            client2.setTelefono("666333222");
            client2.setDireccion("Lanus Oeste");
            client2.setAdmin(false);
            client2 = clientRepository.save(client2);

            ClientEntity client3 = new ClientEntity();
            client3.setNombre("Thiago");
            client3.setApellido("Gomez");
            client3.setEmail("ThiagoMgz@correo.com");
            client3.setPassword("Nickiagoxsiempre");
            client3.setTelefono("666555444");
            client3.setDireccion("Docksud");
            client3.setAdmin(false);
            client3 = clientRepository.save(client3);

            ClientEntity client4 = new ClientEntity();
            client4.setNombre("Francisco");
            client4.setApellido("Gonzalez");
            client4.setEmail("GotenGonzalez@gmail.com");
            client4.setPassword("StrangeDragon777");
            client4.setTelefono("19238512");
            client4.setDireccion("Piñeiro");
            client4.setAdmin(false);
            client4 = clientRepository.save(client4);

            ClientEntity client5 = new ClientEntity();
            client5.setNombre("Roberto");
            client5.setApellido("Kurskman");
            client5.setEmail("RoberKurk@correo.com");
            client5.setPassword("210690");
            client5.setTelefono("126333522");
            client5.setDireccion("Pilar");
            client5.setAdmin(false);
            client5 = clientRepository.save(client5);

            ClientEntity client6 = new ClientEntity();
            client6.setNombre("Olimpiadas");
            client6.setApellido("Admin");
            client6.setEmail("Admin1@correo.com");
            client6.setPassword("Olimpiadas25");
            client6.setTelefono("11112222");
            client6.setDireccion("Calle Ejemplo");
            client6.setAdmin(true);
            client6 = clientRepository.save(client6);

            PaquetEntity paquet1 = new PaquetEntity();
            paquet1.setTipo(pasaje);
            paquet1.setNombre("Paquete Familiar a Bariloche");
            paquet1.setDescripcion("Un viaje inolvidable a Rio Negro - Bariloche, diseñado para tres personas");
            paquet1.setPrecio(800000.0);
            paquet1.setInternacional(false);
            paquet1.setCapacidad(3);
            paquet1.setUbicacion("Argentina - Bariloche");
            paquet1 = paquetRepository.save(paquet1);

            PaquetEntity paquet2 = new PaquetEntity();
            paquet2.setTipo(pasaje);
            paquet2.setNombre("Barco Grupal a Uruguay");
            paquet2.setDescripcion("Experiencia maritima gruál para conocer Colonia con un guia");
            paquet2.setPrecio(400000.0);
            paquet2.setInternacional(true);
            paquet2.setCapacidad(20);
            paquet2.setUbicacion("Uruguay - Colonia");
            paquet2 = paquetRepository.save(paquet2);

            PaquetEntity paquet3 = new PaquetEntity();
            paquet3.setTipo(estadia);
            paquet3.setNombre("Hospedaje en Rio de Janeiro");
            paquet3.setDescripcion("Una casa de ensueño para disfrutar en pareja");
            paquet3.setPrecio(700000.0);
            paquet3.setInternacional(true);
            paquet3.setCapacidad(2);
            paquet3.setUbicacion("Brasil - Rio de Janeiro");
            paquet3 = paquetRepository.save(paquet3);

            PaquetEntity paquet4 = new PaquetEntity();
            paquet4.setTipo(estadia);
            paquet4.setNombre("Hotel Plaza Bariloche");
            paquet4.setDescripcion("Un comodo y famoso hotel con mucho prestigio situado en bariloche");
            paquet4.setPrecio(600000.0);
            paquet4.setInternacional(false);
            paquet4.setCapacidad(3);
            paquet4.setUbicacion("Argentina - Bariloche");
            paquet4 = paquetRepository.save(paquet4);

            PaquetEntity paquet5 = new PaquetEntity();
            paquet5.setTipo(alquiler_auto);
            paquet5.setNombre("Alquiler de Auto para Bariloche");
            paquet5.setDescripcion("Alquiler de un auto 0km para recorrer Bariloche en todo su esplendor");
            paquet5.setPrecio(500000.0);
            paquet5.setInternacional(false);
            paquet5.setCapacidad(4);
            paquet5.setUbicacion("Argentina - Bariloche");
            paquet5 = paquetRepository.save(paquet5);

            PaquetEntity paquet6 = new PaquetEntity();
            paquet6.setTipo(paquete);
            paquet6.setNombre("MegaExperiencia en Bariloche");
            paquet6.setDescripcion("Este es el Kit Bariloche DEFINITIVO. Cuenta con: Un auto 0km, estadia en el Hotel plaza y mas!!");
            paquet6.setPrecio(1700000.0);
            paquet6.setInternacional(false);
            paquet6.setCapacidad(4);
            paquet6.setUbicacion("Hotel de Ejemplo 1");
            paquet6 = paquetRepository.save(paquet6);

            PedidoEntity pedido1 = new PedidoEntity();
            pedido1.setCliente(client1);
            pedido1.setFechainic(LocalDateTime.of(2025, 6, 12, 16, 30));
            pedido1.setFechafin(LocalDateTime.of(2025, 6, 15, 17, 00));
            pedido1.setEstado(pendiente);
            pedido1.setCliente(client1);
            pedido1.setPaquete(paquet1);
            pedido1 = pedidoRepository.save(pedido1);

            PedidoEntity pedido2 = new PedidoEntity();
            pedido2.setCliente(client2);
            pedido2.setFechainic(LocalDateTime.of(2025, 8, 18, 18, 30));
            pedido2.setFechafin(LocalDateTime.of(2025, 8, 26, 12, 00));
            pedido2.setEstado(pendiente);
            pedido2.setCliente(client2);
            pedido2.setPaquete(paquet2);
            pedido2 = pedidoRepository.save(pedido2);

            PedidoEntity pedido3 = new PedidoEntity();
            pedido3.setCliente(client1);
            pedido3.setFechainic(LocalDateTime.of(2025, 10, 10, 12, 30));
            pedido3.setFechafin(LocalDateTime.of(2025, 10, 24, 21, 00));
            pedido3.setEstado(pendiente);
            pedido3.setCliente(client1);
            pedido3.setPaquete(paquet3);
            pedido3 = pedidoRepository.save(pedido3);

            PedidoEntity pedido4 = new PedidoEntity();
            pedido4.setCliente(client3);
            pedido4.setFechainic(LocalDateTime.of(2025, 6, 12, 16, 30));
            pedido4.setFechafin(LocalDateTime.of(2025, 6, 15, 17, 00));
            pedido4.setEstado(pendiente);
            pedido4.setCliente(client3);
            pedido4.setPaquete(paquet6);
            pedido4 = pedidoRepository.save(pedido4);

            PedidoEntity pedido5 = new PedidoEntity();
            pedido5.setCliente(client4);
            pedido5.setFechainic(LocalDateTime.of(2025, 6, 12, 16, 30));
            pedido5.setFechafin(LocalDateTime.of(2025, 6, 15, 17, 00));
            pedido5.setEstado(pendiente);
            pedido5.setCliente(client4);
            pedido5.setPaquete(paquet5);
            pedido5 = pedidoRepository.save(pedido5);

            PedidoEntity pedido6 = new PedidoEntity();
            pedido6.setCliente(client5);
            pedido6.setFechainic(LocalDateTime.of(2025, 6, 12, 16, 30));
            pedido6.setFechafin(LocalDateTime.of(2025, 6, 15, 17, 00));
            pedido6.setEstado(pendiente);
            pedido6.setCliente(client5);
            pedido6.setPaquete(paquet6);
            pedido6 = pedidoRepository.save(pedido1);

        } catch (Exception e) {
            System.err.println("❌ Error al cargar datos de prueba: " + e.getMessage());
            e.printStackTrace();
        } finally {

        }
    }

}
