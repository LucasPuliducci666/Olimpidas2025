package com.example.olimpiadas25.persistence.repository;

import com.example.olimpiadas25.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}