package com.example.olimpiadas25.service;

import com.example.olimpiadas25.dto.request.PedidoRequestDTO;
import com.example.olimpiadas25.dto.response.ClientResponseDTO;
import com.example.olimpiadas25.dto.response.PaquetResponseDTO;
import com.example.olimpiadas25.dto.response.PedidoResponseDTO;
import com.example.olimpiadas25.persistence.entity.*;
import com.example.olimpiadas25.persistence.repository.ClientRepository;
import com.example.olimpiadas25.persistence.repository.PaquetRepository;
import com.example.olimpiadas25.persistence.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

    @Service
    public class PedidoService {

        private final PedidoRepository pedidoRepository;
        private final ClientRepository clientRepository;
        private final PaquetRepository paquetRepository;

        public PedidoService(PedidoRepository pedidoRepository, ClientRepository clientRepository, PaquetRepository paquetRepository) {
            this.pedidoRepository = pedidoRepository;
            this.clientRepository = clientRepository;
            this.paquetRepository = paquetRepository;
        }

        private PedidoResponseDTO toResponseDTO(PedidoEntity pedido) {
            PedidoResponseDTO dto = new PedidoResponseDTO();

            ClientEntity client = pedido.getCliente();
            ClientResponseDTO clientDTO = new ClientResponseDTO();
            clientDTO.setId(client.getId_cliente());
            clientDTO.setNombre(client.getNombre());
            clientDTO.setApellido(client.getApellido());
            clientDTO.setEmail(client.getEmail());
            clientDTO.setTelefono(client.getTelefono());

            PaquetEntity paquet = pedido.getPaquete();
            PaquetResponseDTO paquetDTO = new PaquetResponseDTO();
            paquetDTO.setId(paquet.getId_paquete());
            paquetDTO.setTipo(paquet.getTipo());
            paquetDTO.setNombre(paquet.getNombre());
            paquetDTO.setDescripcion(paquet.getDescripcion());
            paquetDTO.setPrecio(paquet.getPrecio());
            paquetDTO.setCapacidad(paquet.getCapacidad());
            paquetDTO.setUbicacion(paquet.getUbicacion());
            paquetDTO.setInternacional(paquet.getInternacional());

            dto.setId(pedido.getId());
            dto.setCliente(clientDTO);
            dto.setPaquete(paquetDTO);
            dto.setFechainic(pedido.getFechainic());
            dto.setFechafin(pedido.getFechafin());
            dto.setEstado(String.valueOf(pedido.getEstado()));
            return dto;
        }

        public List<PedidoResponseDTO> getAllPedidos() {
            return pedidoRepository.findAll()
                    .stream()
                    .map(this::toResponseDTO)
                    .toList();
        }


        public Optional<PedidoResponseDTO> getPedidoById(Long id) {
            return pedidoRepository.findById(id)
                    .map(this::toResponseDTO);
        }


        public Optional<PedidoResponseDTO> createPedido(PedidoRequestDTO dto) {
            Optional<ClientEntity> clienteOpt = clientRepository.findById(dto.getClienteId());
            Optional<PaquetEntity> paqueteOpt = paquetRepository.findById(dto.getPaqueteId());

            if (clienteOpt.isEmpty() || paqueteOpt.isEmpty()) {
                return Optional.empty();
            }

            PedidoEntity pedido = new PedidoEntity();
            pedido.setCliente(clienteOpt.get());
            pedido.setPaquete(paqueteOpt.get());
            pedido.setFechainic(dto.getFechainic() != null ? dto.getFechainic() : LocalDateTime.now());
            pedido.setFechafin(dto.getFechafin() != null ? dto.getFechafin() : LocalDateTime.now());
            pedido.setEstado(dto.getEstado());

            return Optional.of(toResponseDTO(pedidoRepository.save(pedido)));
        }


        public Optional<PedidoResponseDTO> updatePedido(Long id, PedidoRequestDTO dto) {
            return pedidoRepository.findById(id).flatMap(existing -> {
                Optional<ClientEntity> clienteOpt = clientRepository.findById(dto.getClienteId());
                Optional<PaquetEntity> paqueteOpt = paquetRepository.findById(dto.getPaqueteId());

                if (clienteOpt.isEmpty() || paqueteOpt.isEmpty()) {
                    return Optional.empty();
                }

                existing.setCliente(clienteOpt.get());
                existing.setPaquete(paqueteOpt.get());
                existing.setFechainic(dto.getFechainic() != null ? dto.getFechainic() : LocalDateTime.now());
                existing.setFechafin(dto.getFechafin() != null ? dto.getFechafin() : LocalDateTime.now());
                existing.setEstado(dto.getEstado());

                return Optional.of(toResponseDTO(pedidoRepository.save(existing)));
            });
        }


        public boolean deletePedido(Long id) {
            return pedidoRepository.findById(id).map(pedido -> {
                pedidoRepository.delete(pedido);
                return true;
            }).orElse(false);
        }
    }