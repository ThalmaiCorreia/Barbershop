package com.barbershop.repositories;

import com.barbershop.entities.Commission;
import com.barbershop.entities.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Long> {

    // Buscar todas as comissões de um barbeiro
    List<Commission> findByBarber(Barber barber);

    // Buscar todas as comissões de um barbeiro dentro de um intervalo de datas
    List<Commission> findByBarberAndCommissionDateBetween(Barber barber, OffsetDateTime startDate, OffsetDateTime endDate);

}

