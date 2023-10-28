package com.barbershop.repositories;

import com.barbershop.entities.Barber;
import com.barbershop.entities.Schedule;
import com.barbershop.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findSchedulesByBarber(Barber barber);

    List<Schedule> findAppointmentsByBarberAndDate(Barber barber, Date date);

    List<Schedule> findByBarber(Barber barber);

    @Query("SELECT s FROM Schedule s " +
            "WHERE s.barber = :barber " +
            "AND s.service = :service " +
            "AND s.appointmentDate = :appointmentDate")
    List<Schedule> findByBarberAndServiceAndAppointmentDate(Barber barber, Services service, Date appointmentDate);
}
