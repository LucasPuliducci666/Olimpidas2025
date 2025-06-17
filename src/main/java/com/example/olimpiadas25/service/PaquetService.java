package com.example.olimpiadas25.service;

import com.example.olimpiadas25.dto.request.PaquetRequestDTO;
import com.example.olimpiadas25.dto.response.PaquetResponseDTO;
import com.example.olimpiadas25.persistence.entity.PaquetEntity;
import com.example.olimpiadas25.persistence.entity.Tipo;
import com.example.olimpiadas25.persistence.repository.PaquetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaquetService {

    private final PaquetRepository paquetRepository;

    public PaquetService(PaquetRepository paquetRepository) {
        this.paquetRepository = paquetRepository;
    }

    private PaquetResponseDTO toResponseDTO(PaquetEntity paquet) {
        PaquetResponseDTO dto = new PaquetResponseDTO();
        dto.setId(paquet.getId_paquete());
        dto.setTipo(paquet.getTipo());
        dto.setNombre(paquet.getNombre());
        dto.setDescripcion(paquet.getDescripcion());
        dto.setPrecio(paquet.getPrecio());
        dto.setCapacidad(paquet.getCapacidad());
        dto.setHotel(paquet.getHotel());
        dto.setInternacional(paquet.getInternacional());
        return dto;
    }

    private void fromRequestDTO(PaquetRequestDTO dto, PaquetEntity entity) {
        entity.setTipo(dto.getTipo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPrecio(dto.getPrecio());
        entity.setCapacidad(dto.getCapacidad());
        entity.setHotel(dto.getHotel());
        entity.setInternacional(dto.getInternacional());
    }

    public List<PaquetResponseDTO> getAllPaquetes() {
        return paquetRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Optional<PaquetResponseDTO> getPaquetById(Long id) {
        return paquetRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public PaquetResponseDTO createPaquet(PaquetRequestDTO dto) {
        PaquetEntity paquet = new PaquetEntity();
        fromRequestDTO(dto, paquet);
        return toResponseDTO(paquetRepository.save(paquet));
    }

    public Optional<PaquetResponseDTO> updatePaquet(Long id, PaquetRequestDTO dto) {
        return paquetRepository.findById(id).map(existing -> {
            fromRequestDTO(dto, existing);
            return toResponseDTO(paquetRepository.save(existing));
        });
    }

    public boolean deletePaquet(Long id) {
        return paquetRepository.findById(id).map(p -> {
            paquetRepository.delete(p);
            return true;
        }).orElse(false);
    }

}