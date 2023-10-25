package com.barbershop.repositories;
import com.barbershop.entities.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BarberRepository extends JpaRepository<Barber, Long> {
    List<Barber> findByName(String name);

    Optional<Object> findByEmail(String email);

    Optional<Object> findByPhone(String phone);
}

