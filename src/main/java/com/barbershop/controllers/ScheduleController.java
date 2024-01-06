package com.barbershop.controllers;

import com.barbershop.entities.Barber;
import com.barbershop.entities.Schedule;
import com.barbershop.exceptions.BarberNotFoundException;
import com.barbershop.exceptions.ScheduleNotFoundException;
import com.barbershop.repositories.BarberRepository;
import com.barbershop.services.BarberService;
import com.barbershop.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public BarberService barberService;

    @Autowired
    public BarberRepository barberRepository;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/barber/{barberId}")
    public ResponseEntity<List<Schedule>> getSchedulesByBarber(@PathVariable Long barberId) {
        Barber barber = barberService.findById(barberId);
        List<Schedule> schedules = scheduleService.findSchedulesByBarber(barber);
        return ResponseEntity.ok(schedules);
    }
    @PostMapping("/create")
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        Schedule createdSchedule = scheduleService.createSchedule(schedule);
        return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
    }

    // Mapeamento para o método PUT
    @PutMapping("/{scheduleId}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long scheduleId, @RequestBody Schedule updatedSchedule) {
        try {
            // Chama o método no ScheduleService para atualizar o agendamento
            Schedule updated = scheduleService.updateSchedule(scheduleId, updatedSchedule);
            return ResponseEntity.ok(updated);
        } catch (ScheduleNotFoundException e) {
            // Trata o caso em que o agendamento não foi encontrado
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }
}
