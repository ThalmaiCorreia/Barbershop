package com.barbershop.repositories;

import com.barbershop.entities.Appointment;
import com.barbershop.entities.AppointmentStatus;
import com.barbershop.entities.Barber;
import com.barbershop.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByCustomer(Customer customer);
    List<Appointment> findByBarber(Barber barber);
    List<Appointment> findByStatus(AppointmentStatus status);

    List<Appointment> findByBarberId(Long barberId);
    List<Appointment> findByAppointmentDate(LocalDate date);
}
