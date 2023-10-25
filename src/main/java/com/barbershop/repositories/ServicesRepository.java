package com.barbershop.repositories;

import com.barbershop.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {
    // Métodos de busca padrão já fornecidos pelo Spring Data JPA
}

