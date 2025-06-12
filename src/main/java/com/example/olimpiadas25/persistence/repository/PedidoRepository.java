package com.example.olimpiadas25.persistence.repository;

import com.example.olimpiadas25.persistence.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
}