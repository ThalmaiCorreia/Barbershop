package com.barbershop.repositories;
import com.barbershop.entities.BarberServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarberServicesRepository extends JpaRepository<BarberServices, Long> {
}

