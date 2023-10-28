package com.barbershop.servicies;

import com.barbershop.entities.Barber;
import com.barbershop.entities.Schedule;
import com.barbershop.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> findSchedulesByBarber(Barber barber) {
        return scheduleRepository.findByBarber(barber);
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    public boolean isAppointmentTimeAvailable(Schedule newSchedule) {
        List<Schedule> barbersSchedules = findSchedulesByBarber(newSchedule.getBarber());
        for (Schedule existingSchedule : barbersSchedules) {
            if (hasTimeConflict(newSchedule, existingSchedule)) {
                return false; // Horário não está disponível
            }
        }
        return true; // Horário está disponível
    }

    private boolean hasTimeConflict(Schedule newSchedule, Schedule existingSchedule) {
        // Verifica se há um conflito de horário entre a nova agenda e a agenda existente
        return newSchedule.getOpeningTime().before(existingSchedule.getClosingTime())
                && newSchedule.getClosingTime().after(existingSchedule.getOpeningTime());
    }
}
