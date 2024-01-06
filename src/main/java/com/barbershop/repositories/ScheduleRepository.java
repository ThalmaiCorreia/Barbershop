package com.barbershop.repositories;

import com.barbershop.entities.Barber;
import com.barbershop.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findDistinctByBarber(Barber barber);


}
