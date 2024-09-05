package com.barbershop.controllers;
import com.barbershop.entities.Appointment;
import com.barbershop.entities.Customer;
import com.barbershop.exceptions.BarberNotFoundException;
import com.barbershop.exceptions.CustomerNotFoundException;
import com.barbershop.services.AppointmentService;
import com.barbershop.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);
    private final AppointmentService appointmentService;
    private final CustomerService customerService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, CustomerService customerService) {
        this.appointmentService = appointmentService;
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        logger.info("Fetching all appointments");
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        logger.info("Fetching appointment with id {}", id);
        Appointment appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/barber/{barberId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByBarber(@PathVariable Long barberId) {
        try {
            // Chama o método do serviço para buscar compromissos por ID do barbeiro
            List<Appointment> appointments = appointmentService.findAppointmentsByBarber(barberId);
            return ResponseEntity.ok(appointments);
        } catch (BarberNotFoundException e) {
            // Retorna 404 se o barbeiro não for encontrado
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByCustomer(@PathVariable Long customerId) {
        try {
            // Busca o cliente pelo ID
            Customer customer = customerService.findCustomerById(customerId);
            // Chama o método do serviço para buscar compromissos por cliente
            List<Appointment> appointments = appointmentService.findAppointmentsByCustomer(customer);
            return ResponseEntity.ok(appointments);
        } catch (CustomerNotFoundException e) {
            // Retorna 404 se o cliente não for encontrado
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        logger.info("Creating new appointment");
        Appointment createdAppointment = appointmentService.createAppointment(appointment);
        return ResponseEntity.ok(createdAppointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment updatedAppointment) {
        logger.info("Updating appointment with id {}", id);
        Appointment updated = appointmentService.updateAppointment(id, updatedAppointment);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        logger.info("Deleting appointment with id {}", id);
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}