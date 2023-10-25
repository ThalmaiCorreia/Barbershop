package com.barbershop.repositories;
import com.barbershop.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);

    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByPhone(String phone);
}

