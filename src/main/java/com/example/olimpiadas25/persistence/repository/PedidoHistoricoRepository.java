package com.example.olimpiadas25.persistence.repository;

import com.example.olimpiadas25.persistence.entity.PedidoHistoricoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoHistoricoRepository extends JpaRepository<PedidoHistoricoEntity, Long> {
}

