package com.barbershop.services;

import com.barbershop.entities.Barber;
import com.barbershop.entities.Schedule;
import com.barbershop.exceptions.BarberNotFoundException;
import com.barbershop.exceptions.ScheduleConflictException;
import com.barbershop.exceptions.ScheduleNotFoundException;
import com.barbershop.repositories.BarberRepository;
import com.barbershop.repositories.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private final ScheduleRepository scheduleRepository;
    @Autowired
    private final BarberRepository barberRepository;
    private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);


    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, BarberRepository barberRepository) {
        this.scheduleRepository = scheduleRepository;
        this.barberRepository = barberRepository;
    }

    public List<Schedule> findSchedulesByBarber(Barber barber) {
        return scheduleRepository.findDistinctByBarber(barber);
    }

    @Transactional
    public Schedule createSchedule(Schedule schedule) {
        validateBarber(schedule);


        // Agora você pode salvar o Schedule dentro de uma transação
        return scheduleRepository.save(schedule);
    }

    @Transactional
    public Schedule updateSchedule(Long scheduleId, Schedule updatedSchedule) {
        // Verifica se o agendamento existe
        Schedule existingSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ScheduleNotFoundException("Agendamento não encontrado com ID: " + scheduleId));

        // Atualiza os campos do agendamento existente com os valores do agendamento atualizado
        existingSchedule.setBarber(updatedSchedule.getBarber());
        existingSchedule.setOpeningTime(updatedSchedule.getOpeningTime());
        existingSchedule.setLunchBreakStart(updatedSchedule.getLunchBreakStart());
        existingSchedule.setLunchBreakEnd(updatedSchedule.getLunchBreakEnd());
        existingSchedule.setClosingTime(updatedSchedule.getClosingTime());

        // Salva o agendamento atualizado
        return scheduleRepository.save(existingSchedule);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        logger.info("Trying to delete schedule with ID: {}", scheduleId);

        if (scheduleRepository.existsById(scheduleId)) {
            logger.info("Schedule found. Deleting...");
            scheduleRepository.deleteById(scheduleId);
            logger.info("Schedule deleted successfully.");
        } else {
            logger.warn("Schedule not found with ID: {}", scheduleId);
            throw new ScheduleNotFoundException("Schedule not found with ID: " + scheduleId);
        }
    }


    public boolean isAppointmentTimeAvailable(Schedule schedule) {
        // Implementar a lógica para verificar se o horário de agendamento está disponível
        // Retorne true se estiver disponível, caso contrário, retorne false.
        // Esta é uma implementação de exemplo, substitua conforme necessário.
        return true;
    }

    private void validateBarber(Schedule schedule) {
        Barber barber = schedule.getBarber();
        // Verificar se o ID do barbeiro é nulo
        if (barber.getId() == null) {
            throw new BarberNotFoundException("ID do barbeiro é nulo. O barbeiro deve ser persistido antes de criar a agenda.");
        }

        // Verificar se o barbeiro realmente existe no banco de dados
        if (!barberRepository.existsById(barber.getId())) {
            throw new BarberNotFoundException("Barbeiro com ID " + barber.getId() + " não encontrado.");
        }
    }
}
