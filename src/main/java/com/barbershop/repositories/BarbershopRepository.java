package com.barbershop.repositories;
import com.barbershop.entities.BarberShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarbershopRepository extends JpaRepository<BarberShop, Long> {

}

