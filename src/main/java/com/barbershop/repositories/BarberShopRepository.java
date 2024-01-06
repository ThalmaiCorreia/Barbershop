package com.barbershop.repositories;
import com.barbershop.entities.BarberShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarberShopRepository extends JpaRepository<BarberShop, Long> {

    Optional<BarberShop> findByName(String name);

    boolean existsByName(String name);


    boolean existsByNameIgnoreCaseAndIdNot(String updatedName, Long id);
}

